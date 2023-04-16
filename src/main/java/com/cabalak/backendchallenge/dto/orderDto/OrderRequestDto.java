package com.cabalak.backendchallenge.dto.orderDto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequestDto(
        @NotNull(message = "{constraints.OrderRequestDto.totalPriceNotNull}")
        BigDecimal totalPrice,

        @NotNull(message = "{constraints.OrderRequestDto.customerIdNotNull}")
        String customerId
) {
}
