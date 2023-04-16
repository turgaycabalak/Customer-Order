package com.cabalak.backendchallenge.dto.orderDto;

import java.math.BigDecimal;

public record OrderUpdateRequestDto(
        BigDecimal totalPrice
) {
}
