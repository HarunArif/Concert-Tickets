package com.testcodekonser.testcode_konser.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="m_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fullName;
    @Column(unique = true)
    private String email;
    @Column(unique = true, name = "phone_number")
    private String phoneNumber;
    private String address;

    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Jakarta")
    private Date birthDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", timezone = "Asia/Jakarta")
    private Date joinDate;

    @OneToOne
    private UserCredential userCredential;

    @PrePersist
    protected void onCreate(){
        joinDate = new Date();
    }
}
