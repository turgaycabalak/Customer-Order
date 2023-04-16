package com.cabalak.backendchallenge.controller;

import com.cabalak.backendchallenge.dto.customerDto.CustomerRequestDto;
import com.cabalak.backendchallenge.genericResponse.SuccessDataResult;
import com.cabalak.backendchallenge.genericResponse.SuccessResult;
import com.cabalak.backendchallenge.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.createCustomer(customerRequestDto), "Customer created."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") String customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(new SuccessResult("Customer successfully deleted. Customer Id : " + customerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") String customerId, @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.updateCustomer(customerId, customerRequestDto), "Customer successfully updated."));
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.getAllCustomers(), "All customers fetched successfully."));
    }

    @GetMapping("/slice")
    public ResponseEntity<?> getAllCustomersBySlice(Pageable pageable) {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.getAllCustomersBySlice(pageable), "Customers fetched successfully by pagination."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable("id") String customerId) {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.getCustomerById(customerId), "Customer found by id : " + customerId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomersAndOrders(@RequestParam("customerName") String customerName) {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.searchCustomersAndOrders(customerName), "Customers you searched listed."));
    }

    @GetMapping("/search-empty")
    public ResponseEntity<?> findByOrdersIsEmpty() {
        return ResponseEntity.ok(new SuccessDataResult<>(customerService.findByOrdersIsEmpty(), "Customers you searched listed."));
    }


}
