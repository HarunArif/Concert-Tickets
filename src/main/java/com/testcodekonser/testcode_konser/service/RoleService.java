package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.constant.ERole;
import com.testcodekonser.testcode_konser.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
