package com.cabalak.backendchallenge.dto.orderDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponseDto(
        String id,
        LocalDateTime createDate,
        BigDecimal totalPrice
//        String customerId
) {
}
