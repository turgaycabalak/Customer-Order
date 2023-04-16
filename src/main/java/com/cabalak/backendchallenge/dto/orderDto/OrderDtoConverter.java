package com.cabalak.backendchallenge.dto.orderDto;

import com.cabalak.backendchallenge.dto.customerDto.CustomerResponseDto;
import com.cabalak.backendchallenge.model.Customer;
import com.cabalak.backendchallenge.model.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderDtoConverter {

    public OrderResponseDto convertToOrderResponseDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getCreateDate(),
                order.getTotalPrice()
        );
    }

    public List<OrderResponseDto> convertToListOrderResponseDto(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertToOrderResponseDto)
                .toList();
    }

    public Order convertToOrder(OrderRequestDto orderRequestDto, Customer customer) {
        return new Order(
                LocalDateTime.now(),
                orderRequestDto.totalPrice(),
                customer
        );
    }

    public OrderWithCustomerResponseDto convertToOrderWithCustomerResponseDto(Order order) {
        return new OrderWithCustomerResponseDto(
                order.getId(),
                order.getCreateDate(),
                order.getTotalPrice(),
                new CustomerResponseDto(
                        order.getCustomer().getId(),
                        order.getCustomer().getName(),
                        order.getCustomer().getAge()
                )
        );
    }

    public List<OrderWithCustomerResponseDto> convertToListOrderWithCustomerResponseDto(List<Order> orderList) {
        return orderList.stream()
                .map(this::convertToOrderWithCustomerResponseDto)
                .toList();
    }
}
