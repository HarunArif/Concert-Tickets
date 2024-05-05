package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.model.request.TicketPaymentRequest;
import com.testcodekonser.testcode_konser.model.response.TicketPaymentResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.service.TicketPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket-payment")
@AllArgsConstructor
public class TicketPaymentController {
    private final TicketPaymentService ticketPaymentService;

    @PostMapping
    public ResponseEntity<WebResponse<TicketPaymentResponse>> createTicketPayment(@RequestBody TicketPaymentRequest ticketPaymentRequest) {
        TicketPaymentResponse paymentResponse = ticketPaymentService.create(ticketPaymentRequest);
        WebResponse<TicketPaymentResponse> response = WebResponse.<TicketPaymentResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("Success create payment")
                .data(paymentResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
