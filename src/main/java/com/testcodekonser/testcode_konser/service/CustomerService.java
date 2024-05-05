package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    Page<Customer> getAllCustomer(Integer page, Integer size);

    Customer getCustomerById(String id);

    Customer updateCustomer(Customer customer);

    void  deleteCustomerById(String id);
}
