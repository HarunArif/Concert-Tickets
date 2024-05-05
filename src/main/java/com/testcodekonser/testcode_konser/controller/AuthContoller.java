package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.model.request.AuthRequest;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthContoller {
    private final AuthService authService;

    @PostMapping("/register-customer")
    public ResponseEntity<WebResponse<CustomerResponse>> createNasabah(@Valid @RequestBody CustomerRequest customerRequest){
       CustomerResponse customerResponse = authService.register(customerRequest);
        WebResponse<CustomerResponse> response = WebResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Resgister New Customer")
                .data(customerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        String token = authService.login(authRequest);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success Login")
                .data(token)
                .build();
        return ResponseEntity.ok(response);
    }
}
