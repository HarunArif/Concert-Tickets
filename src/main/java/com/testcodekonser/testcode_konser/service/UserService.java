package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserCredential loadByUserId(String userId);
}
