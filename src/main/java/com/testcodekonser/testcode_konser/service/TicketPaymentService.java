package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.model.request.TicketPaymentRequest;
import com.testcodekonser.testcode_konser.model.response.TicketPaymentResponse;

import java.util.List;

public interface TicketPaymentService {
    TicketPaymentResponse create(TicketPaymentRequest ticketPaymentRequest);

    List<TicketPaymentResponse> getAll();
}
