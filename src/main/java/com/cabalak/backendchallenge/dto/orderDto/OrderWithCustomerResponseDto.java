package com.cabalak.backendchallenge.dto.orderDto;

import com.cabalak.backendchallenge.dto.customerDto.CustomerResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderWithCustomerResponseDto(
        String id,
        LocalDateTime createDate,
        BigDecimal totalPrice,
        CustomerResponseDto customer
) {
}
