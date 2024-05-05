package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
