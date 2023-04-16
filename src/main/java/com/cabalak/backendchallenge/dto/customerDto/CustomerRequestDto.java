package com.cabalak.backendchallenge.dto.customerDto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerRequestDto(
        @NotNull(message = "{constraints.CustomerRequestDto.NameNotNull}")
        @Size(min = 2, max = 32, message = "{constraints.CustomerRequestDto.NameSize}")
        String name,

        @Min(value = 1, message = "{constraints.CustomerRequestDto.AgeMin}")
        int age
) {
}
