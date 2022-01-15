package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class ProductDto {
    private final long id;
    private final String serialNumber;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final long shopId;
}
