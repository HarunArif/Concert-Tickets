package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.model.request.AuthRequest;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;

public interface AuthService {
    CustomerResponse register(CustomerRequest customerRequest);

    String login(AuthRequest authRequest);
}
