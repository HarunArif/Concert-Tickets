package com.testcodekonser.testcode_konser.controller;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.model.response.PagingResponse;
import com.testcodekonser.testcode_konser.model.response.WebResponse;
import com.testcodekonser.testcode_konser.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomer(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Page<Customer> customerList = customerService.getAllCustomer(page, size);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(page).size(size)
                .totalPages(customerList.getTotalPages())
                .totalElement(customerList.getTotalElements())
                .build();

        WebResponse<List<Customer>> response = WebResponse.<List<Customer>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get List data")
                .paging(pagingResponse)
                .data(customerList.getContent())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id){
        Customer findCustomer = customerService.getCustomerById(id);
        WebResponse<Customer> response = WebResponse.<Customer>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Get Customer By Id")
                .data(findCustomer)
                .build();
        return ResponseEntity.ok(response);
    }



    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id){
        customerService.deleteCustomerById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Delete Customer By Id ")
                .data("OK")
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomerById(@RequestBody Customer customer){
        Customer updateCustomer = customerService.updateCustomer(customer);
        WebResponse<Customer> response = WebResponse.<Customer>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("Success Update customer By Id ")
                .data(updateCustomer)
                .build();
        return ResponseEntity.ok(response);
    }
}
