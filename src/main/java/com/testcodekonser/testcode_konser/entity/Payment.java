package com.testcodekonser.testcode_konser.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String token;
    private String redirectUrl;
    private String transactionStatus;

    @OneToOne(mappedBy = "payment")
    private TicketPayment ticketPayment;
}
