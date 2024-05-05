package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.entity.Customer;
import com.testcodekonser.testcode_konser.model.request.CustomerRequest;
import com.testcodekonser.testcode_konser.model.response.CustomerResponse;
import com.testcodekonser.testcode_konser.repository.CustomerRepository;
import com.testcodekonser.testcode_konser.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer newCustomer = Customer.builder()
                .fullName(customerRequest.getFullName())
                .email(customerRequest.getEmail())
                .phoneNumber(customerRequest.getPhoneNumber())
                .address(customerRequest.getAddress())
                .birthDate(customerRequest.getBirthDate())
                .build();
        Customer registCustomer = customerRepository.saveAndFlush(newCustomer);
        return CustomerResponse.builder()
                .fullName(registCustomer.getFullName())
                .email(registCustomer.getEmail())
                .phoneNumber(registCustomer.getPhoneNumber())
                .address(registCustomer.getAddress())
                .birthDate(registCustomer.getBirthDate())
                .build();
    }

    @Override
    public Page<Customer> getAllCustomer(Integer page, Integer size) {
        if (page <=0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page-1, size);
        return customerRepository.findAll(pageable);

    }

    @Override
    public Customer getCustomerById(String id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id : " + id + " Not Found");
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        this.getCustomerById(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(String id) {
        this.getCustomerById(id);
        customerRepository.deleteById(id);
    }
}
