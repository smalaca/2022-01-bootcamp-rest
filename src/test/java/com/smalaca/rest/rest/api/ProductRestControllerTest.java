package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

class ProductRestControllerTest {
    private static final String PRODUCTS_RESOURCE = "http://localhost:8013/products";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldReturnAllProducts() {
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Coffee", BigDecimal.valueOf(12.34), "The best product of the world", 1L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Tea", BigDecimal.valueOf(10.01), "Worth to drink from time to time", 1L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Pillow", BigDecimal.valueOf(12.34), "You need it when you want to sleep", 2L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Light saber", BigDecimal.valueOf(12.34), "You have to have it", 3L), Long.class);

        ProductTestDto[] actual = client.getForObject(PRODUCTS_RESOURCE, ProductTestDto[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class ProductTestDto {
        private long id;
        private String serialNumber;
        private String name;
        private BigDecimal price;
        private String description;
        private long shopId;

        ProductTestDto(String serialNumber, String name, BigDecimal price, String description, long shopId) {
            this.serialNumber = serialNumber;
            this.name = name;
            this.price = price;
            this.description = description;
            this.shopId = shopId;
        }
    }
}