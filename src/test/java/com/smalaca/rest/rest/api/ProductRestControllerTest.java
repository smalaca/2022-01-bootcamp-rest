package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

class ProductRestControllerTest {
    private static final String PRODUCTS_RESOURCE = "http://localhost:8013/products";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnAllProducts() {
        ProductTestDto[] actual = client.getForObject(PRODUCTS_RESOURCE, ProductTestDto[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Getter
    @ToString
    public static class ProductTestDto {
        private long id;
        private String serialNumber;
        private String name;
        private BigDecimal price;
        private String description;
        private long shopId;
    }
}