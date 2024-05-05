package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.Payment;
import com.testcodekonser.testcode_konser.entity.TicketPayment;

public interface PaymentService {
    Payment create(TicketPayment ticketPayment);
}
