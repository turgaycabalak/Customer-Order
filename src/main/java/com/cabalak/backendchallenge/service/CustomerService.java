package com.cabalak.backendchallenge.service;

import com.cabalak.backendchallenge.dto.customerDto.*;
import com.cabalak.backendchallenge.exceptions.NotFoundException;
import com.cabalak.backendchallenge.model.Customer;
import com.cabalak.backendchallenge.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerServiceImpl {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    protected Customer getUserById(String customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found : " + customerId));
    }

    @Override
    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = customerDtoConverter.convertToCustomer(customerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerDtoConverter.convertToCustomerResponseDto(savedCustomer);
    }

    @Override
    @Transactional
    public void deleteCustomer(String customerId) {
        boolean existed = customerRepository.existsById(customerId);
        if (!existed) throw new NotFoundException("Customer not found : " + customerId);

        customerRepository.deleteById(customerId);
    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomer(String customerId, CustomerRequestDto customerRequestDto) {
        Customer customerDb = getUserById(customerId);
        customerDb.setName(customerRequestDto.name());
        customerDb.setAge(customerRequestDto.age());

        return customerDtoConverter.convertToCustomerResponseDto(customerRepository.save(customerDb));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerListDb = customerRepository.findAll();

        return customerDtoConverter.convertToListCustomerResponseDto(customerListDb);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<CustomerResponseDto> getAllCustomersBySlice(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        List<CustomerResponseDto> customerResponseDtos = customerDtoConverter.convertToListCustomerResponseDto(customerPage.getContent());

        return new PageImpl<>(customerResponseDtos, customerPage.getPageable(), customerPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CustomerWithOrdersResponseDto getCustomerById(String customerId) {
        Customer customerDb = getUserById(customerId);

        return customerDtoConverter.convertToCustomerWithOrdersResponseDto(customerDb);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CustomerWithOrderIdsResponseDto> searchCustomersAndOrders(String customerName) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(customerName);

        return customerDtoConverter.convertToListCustomerWithOrderIdsResponseDto(customers);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Customer> findByOrdersIsEmpty() {
        return customerRepository.findByOrdersIsEmpty();
    }


}
