package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.entity.Payment;
import com.testcodekonser.testcode_konser.entity.Ticket;
import com.testcodekonser.testcode_konser.entity.TicketPayment;
import com.testcodekonser.testcode_konser.model.request.TicketPaymentRequest;
import com.testcodekonser.testcode_konser.model.response.PaymentResponse;
import com.testcodekonser.testcode_konser.model.response.TicketPaymentResponse;
import com.testcodekonser.testcode_konser.repository.TicketPaymentRepository;
import com.testcodekonser.testcode_konser.service.PaymentService;
import com.testcodekonser.testcode_konser.service.TicketPaymentService;
import com.testcodekonser.testcode_konser.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketPaymentServiceImpl implements TicketPaymentService {
    private final TicketPaymentRepository ticketPaymentRepository;
    private final PaymentService paymentService;
    private final TicketService ticketService;
    @Override
    public TicketPaymentResponse create(TicketPaymentRequest ticketPaymentRequest) {
        Ticket findTicket = ticketService.getById(ticketPaymentRequest.getTicketId());
        Long amount = (long) findTicket.getPrice();
        TicketPayment ticketPayment = TicketPayment.builder()
                .amount(amount)
                .ticket(findTicket)
                .build();

        TicketPayment savedTicketPayment = ticketPaymentRepository.saveAndFlush(ticketPayment);
        Payment payment = paymentService.create(savedTicketPayment);
        savedTicketPayment.setPayment(payment);
        PaymentResponse paymentResponse = PaymentResponse.builder()
                .id(payment.getId())
                .token(payment.getToken())
                .redirectUrl(payment.getRedirectUrl())
                .transactionStatus(payment.getTransactionStatus())
                .build();
        return TicketPaymentResponse.builder()
                .id(savedTicketPayment.getId())
                .ticketId(findTicket.getId())
                .amount(amount)
                .date(String.valueOf(savedTicketPayment.getDate()))
                .paymentResponse(paymentResponse)
                .build();
    }

    @Override
    public List<TicketPaymentResponse> getAll() {
        return null;
    }
}
