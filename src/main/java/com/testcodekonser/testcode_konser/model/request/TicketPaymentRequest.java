package com.testcodekonser.testcode_konser.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketPaymentRequest {
    private String ticketId;
}
