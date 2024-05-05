package com.testcodekonser.testcode_konser.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private String id;
    private String token;
    private String redirectUrl;
    private String transactionStatus;
}
