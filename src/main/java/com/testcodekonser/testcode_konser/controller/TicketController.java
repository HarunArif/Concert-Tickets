package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.entity.Ticket;
import com.testcodekonser.testcode_konser.model.request.TicketRequest;
import com.testcodekonser.testcode_konser.model.response.PagingResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        Ticket purchasedTicket = ticketService.purchaseTicket(ticketRequest);
        return new ResponseEntity<>(purchasedTicket, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllTicket(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Page<Ticket> ticketList = ticketService.getAllTicket(page, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page).size(size)
                .totalPages(ticketList.getTotalPages())
                .totalElement(ticketList.getTotalElements())
                .build();

        WebResponse<List<Ticket>> response = WebResponse.<List<Ticket>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List data")
                .paging(pagingResponse)
                .data(ticketList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Ticket findTicket = ticketService.getById(id);
        WebResponse<Ticket> response = WebResponse.<Ticket>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Day By Id")
                .data(findTicket)
                .build();
        return ResponseEntity.ok(response);
    }

}
