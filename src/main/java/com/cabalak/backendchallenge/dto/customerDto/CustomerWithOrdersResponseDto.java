package com.cabalak.backendchallenge.dto.customerDto;

import com.cabalak.backendchallenge.dto.orderDto.OrderResponseDto;

import java.util.List;

public record CustomerWithOrdersResponseDto(
        String id,
        String name,
        int age,
        List<OrderResponseDto> orders
) {
}
