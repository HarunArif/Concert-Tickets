package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.constant.ERole;
import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.Role;
import com.testcodekonser.testcode_konser.entity.UserCredential;
import com.testcodekonser.testcode_konser.model.request.AuthRequest;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.repository.UserCredentialRepository;
import com.testcodekonser.testcode_konser.security.JwtUtils;
import com.testcodekonser.testcode_konser.service.AuthService;
import com.testcodekonser.testcode_konser.service.CustomerService;
import com.testcodekonser.testcode_konser.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleService roleService;
    private final UserCredentialRepository credentialRepository;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Value("${app.testcode-konser.username-admin}")
    private String usernameAdmin;
    @Value("${app.testcode-konser.password-admin}")
    private String passwordAdmin;

    @PostConstruct
    public void initSuperAdmin(){
        Optional<UserCredential> optionalUserCred = credentialRepository.findByUsername(usernameAdmin);
        if (optionalUserCred.isPresent()) return;

        Role superAdminRole = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);
        Role adminRole = roleService.getOrSave(ERole.ROLE_ADMIN);
        Role customerRole = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        String hashPassword = passwordEncoder.encode(passwordAdmin);

        UserCredential userCredential = UserCredential.builder()
                .username(usernameAdmin)
                .password(hashPassword)
                .roles(List.of(superAdminRole,adminRole,customerRole))
                .build();
        credentialRepository.saveAndFlush(userCredential);
    }
    @Override
    public CustomerResponse register(CustomerRequest customerRequest) {

        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        //hash Password
        String hashPassword = passwordEncoder.encode(customerRequest.getPassword());
        CustomerResponse newCustomer = customerService.createCustomer(customerRequest);
        UserCredential userCredential = UserCredential.builder()
                .username(customerRequest.getUsername())
                .password(hashPassword)
                .roles(List.of(roleCustomer))
                .build();
        UserCredential savedUserCredential = credentialRepository.saveAndFlush(userCredential);
        //List Role
        List<String> roles = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();
        return CustomerResponse.builder()
                .fullName(newCustomer.getFullName())
                .email(newCustomer.getEmail())
                .phoneNumber(newCustomer.getPhoneNumber())
                .address(newCustomer.getAddress())
                .username(savedUserCredential.getUsername())
                .birthDate(newCustomer.getBirthDate())
                .roles(roles)
                .build();
    }

    @Override
    public String login(AuthRequest authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );
        Authentication authenticated = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        UserCredential userCredential = (UserCredential) authenticated.getPrincipal();
        return jwtUtils.generateToken(userCredential);
    }
}
