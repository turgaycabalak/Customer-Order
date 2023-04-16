package com.cabalak.backendchallenge.repository;

import com.cabalak.backendchallenge.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    List<Customer> findByNameContainingIgnoreCase(String name);

    List<Customer> findByOrdersIsNull();

    List<Customer> findByOrdersIsEmpty();

}
