package com.cabalak.backendchallenge.service;

import com.cabalak.backendchallenge.dto.customerDto.CustomerRequestDto;
import com.cabalak.backendchallenge.dto.customerDto.CustomerResponseDto;
import com.cabalak.backendchallenge.dto.customerDto.CustomerWithOrderIdsResponseDto;
import com.cabalak.backendchallenge.dto.customerDto.CustomerWithOrdersResponseDto;
import com.cabalak.backendchallenge.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerServiceImpl {
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    void deleteCustomer(String customerId);

    CustomerResponseDto updateCustomer(String customerId, CustomerRequestDto customerRequestDto);

    List<CustomerResponseDto> getAllCustomers();

    Page<CustomerResponseDto> getAllCustomersBySlice(Pageable pageable);

    CustomerWithOrdersResponseDto getCustomerById(String customerId);

    List<CustomerWithOrderIdsResponseDto> searchCustomersAndOrders(String customerName);

    List<Customer> findByOrdersIsEmpty();
}
