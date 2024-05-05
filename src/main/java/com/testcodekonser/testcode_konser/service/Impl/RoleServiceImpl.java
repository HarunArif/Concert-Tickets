package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.constant.ERole;
import com.testcodekonser.testcode_konser.entity.Role;
import com.testcodekonser.testcode_konser.repository.RoleRepository;
import com.testcodekonser.testcode_konser.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if (optionalRole.isPresent()) return optionalRole.get();

        return roleRepository.saveAndFlush(
                Role.builder()
                        .role(role)
                        .build()
        );
    }
}
