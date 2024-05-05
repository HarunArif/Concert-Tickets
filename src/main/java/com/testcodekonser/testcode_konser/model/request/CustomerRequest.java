package com.testcodekonser.testcode_konser.model.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank(message = "Fullname is mandatory and cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Fullname must contain only alphabet")
    private String fullName;
    @NotBlank(message = "Fullname is mandatory and cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must containt only numbers")
    @NotBlank(message = "Fullname is mandatory and cannot be blank")
    private String phoneNumber;
    private String address;
    private Date birthDate;
    private String username;
    private String password;

}
