package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.constant.ERole;
import com.testcodekonser.testcode_konser.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);
}
