package com.testcodekonser.testcode_konser.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Date birthDate;
    private String username;
    private List<String> roles;
}
