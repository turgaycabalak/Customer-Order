package com.cabalak.backendchallenge.service;

import com.cabalak.backendchallenge.dto.orderDto.OrderRequestDto;
import com.cabalak.backendchallenge.dto.orderDto.OrderResponseDto;
import com.cabalak.backendchallenge.dto.orderDto.OrderWithCustomerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceImpl {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    void deleteOrder(String orderId);

    OrderResponseDto updateOrder(String orderId, OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getAllOrders();

    Page<OrderResponseDto> getAllOrdersBySlice(Pageable pageable);

    OrderWithCustomerResponseDto getOrderById(String orderId);

    List<OrderWithCustomerResponseDto> getAllOrdersAfterDate(LocalDateTime date);
}
