package com.smalaca.rest.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private String description;
    private long shopId;
    private String creationHost;
    private String creationUser;
}
