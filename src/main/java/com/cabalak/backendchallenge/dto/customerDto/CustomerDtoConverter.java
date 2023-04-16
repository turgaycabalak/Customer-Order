package com.cabalak.backendchallenge.dto.customerDto;

import com.cabalak.backendchallenge.dto.orderDto.OrderDtoConverter;
import com.cabalak.backendchallenge.dto.orderDto.OrderResponseDto;
import com.cabalak.backendchallenge.model.Customer;
import com.cabalak.backendchallenge.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerDtoConverter {

    private final OrderDtoConverter orderDtoConverter;

    public CustomerResponseDto convertToCustomerResponseDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getAge()
        );
    }

    public Customer convertToCustomer(CustomerRequestDto customerRequestDto) {
        return new Customer(
                customerRequestDto.name(),
                customerRequestDto.age()
        );
    }

    public List<CustomerResponseDto> convertToListCustomerResponseDto(List<Customer> customerList) {
        return customerList.stream()
                .map(this::convertToCustomerResponseDto)
                .toList();
    }

    public CustomerWithOrdersResponseDto convertToCustomerWithOrdersResponseDto(Customer customer) {
        List<Order> orders = customer.getOrders();
        List<OrderResponseDto> orderResponseDtos = orderDtoConverter.convertToListOrderResponseDto(orders);

        return new CustomerWithOrdersResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                orderResponseDtos
        );
    }

    public CustomerWithOrderIdsResponseDto convertToCustomerWithOrderIdsResponseDto(Customer customer) {
        return new CustomerWithOrderIdsResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getOrders().stream()
                        .map(Order::getId)
                        .toList()
        );
    }

    public List<CustomerWithOrderIdsResponseDto> convertToListCustomerWithOrderIdsResponseDto(List<Customer> customers) {
        return customers.stream()
                .map(this::convertToCustomerWithOrderIdsResponseDto)
                .toList();
    }

}
