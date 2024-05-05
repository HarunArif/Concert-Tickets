package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {
    Optional<UserCredential> findByUsername(String username);
}
