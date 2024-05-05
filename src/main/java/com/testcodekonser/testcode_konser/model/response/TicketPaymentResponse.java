package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPaymentResponse {
    private String id;
    private String ticketId;
    private Long amount;
    private String date;
    private PaymentResponse paymentResponse;
}
