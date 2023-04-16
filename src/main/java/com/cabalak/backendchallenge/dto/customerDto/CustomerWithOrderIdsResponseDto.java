package com.cabalak.backendchallenge.dto.customerDto;

import java.util.List;

public record CustomerWithOrderIdsResponseDto(
        String id,
        String name,
        int age,
        List<String> orderIds
) {
}
