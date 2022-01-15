package com.smalaca.rest.rest.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

class ProductRestControllerTest {
    private static final String PRODUCTS_RESOURCE = "http://localhost:8013/products/";
    private final RestTemplate client = new RestTemplate();

    @Test
    void shouldCreateProductWithHeaderValue() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("username", "doctorstrange");
        HttpEntity<ProductTestDto> entity = new HttpEntity<>(
                new ProductTestDto(UUID.randomUUID().toString(), "Orange juice", BigDecimal.valueOf(5.43), "It's orange and it's good", 1L), httpHeaders);

        ResponseEntity<Long> response = client.exchange(PRODUCTS_RESOURCE, HttpMethod.POST, entity, Long.class);

        ProductTestDto actual = client.getForObject(PRODUCTS_RESOURCE + response.getBody(), ProductTestDto.class);

        System.out.println(actual);
    }

    @Test
    void shouldCreateProduct() {
        Long id = client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Orange juice", BigDecimal.valueOf(5.43), "It's orange and it's good", 1L), Long.class);

        ProductTestDto actual = client.getForObject(PRODUCTS_RESOURCE + id, ProductTestDto.class);

        System.out.println(actual);
    }

    @Test
    void shouldCreateProductWithTheSameSerialNumber() {
        String serialNumber = UUID.randomUUID().toString();
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(serialNumber, "Orange juice", BigDecimal.valueOf(5.43), "It's orange and it's good", 1L), Long.class);

        try {
            client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(serialNumber, "Orange juice", BigDecimal.valueOf(5.43), "It's orange and it's good", 1L), Long.class);
        } catch (HttpClientErrorException exception) {
            System.out.println("-----------------------");
            System.out.println(exception.getStatusCode());
        }
    }

    @Test
    void shouldFindNotExistingProduct() {
        try {
            client.getForObject(PRODUCTS_RESOURCE + RandomUtils.nextLong(), ProductTestDto.class);
        } catch (HttpClientErrorException exception) {
            System.out.println("-----------------------");
            System.out.println(exception.getStatusCode());
        }
    }

    @Test
    void shouldDeleteProduct() {
        Long id = client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Something", BigDecimal.valueOf(99.88), "No one knows", 13L), Long.class);

        client.delete(PRODUCTS_RESOURCE + id);

        ProductTestDto[] actual = client.getForObject(PRODUCTS_RESOURCE, ProductTestDto[].class);
        Arrays.asList(actual).forEach(System.out::println);
    }

    @Test
    void shouldDeleteNotExistingProduct() {
        try {
            client.delete(PRODUCTS_RESOURCE + RandomUtils.nextLong());
        } catch (HttpClientErrorException exception) {
            System.out.println("-----------------------");
            System.out.println(exception.getStatusCode());
        }
    }

    @Test
    void shouldUpdateProduct() {
        Long id = client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Apple juice", BigDecimal.valueOf(5.43), "who does not like apples", 1L), Long.class);
        client.put(PRODUCTS_RESOURCE + id, new ProductTestDto(BigDecimal.valueOf(100.01), "The best drink ever", 42L));

        ProductTestDto actual = client.getForObject(PRODUCTS_RESOURCE + id, ProductTestDto.class);

        System.out.println(actual);
    }

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

    @Test
    void shouldReturnAllProductsForGivenShop() {
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Coffee", BigDecimal.valueOf(12.34), "The best product of the world", 21L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Tea", BigDecimal.valueOf(10.01), "Worth to drink from time to time", 21L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Pillow", BigDecimal.valueOf(12.34), "You need it when you want to sleep", 42L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Light saber", BigDecimal.valueOf(12.34), "You have to have it", 3L), Long.class);

        ProductTestDto[] actual = client.getForObject(PRODUCTS_RESOURCE + "?shopIds=21&shopIds=42", ProductTestDto[].class);

        Arrays.asList(actual).forEach(System.out::println);
    }

    @Test
    void shouldReturnAllProductsByNameOrSerialNumber() {
        String serialNumber = UUID.randomUUID().toString();
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(serialNumber, "Coffee", BigDecimal.valueOf(12.34), "The best product of the world", 1L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Tea", BigDecimal.valueOf(10.01), "Worth to drink from time to time", 1L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "Pillow", BigDecimal.valueOf(12.34), "You need it when you want to sleep", 2L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "LightSaber", BigDecimal.valueOf(12.34), "You have to have it", 3L), Long.class);
        client.postForObject(PRODUCTS_RESOURCE,
                new ProductTestDto(UUID.randomUUID().toString(), "LightSaber", BigDecimal.valueOf(12.34), "this is red one", 3L), Long.class);

        ProductTestDto[] actual = client.getForObject(PRODUCTS_RESOURCE + "?name=LightSaber&serialNumber=" + serialNumber, ProductTestDto[].class);

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
        private String creationHost;
        private String creationUser;

        ProductTestDto(String serialNumber, String name, BigDecimal price, String description, long shopId) {
            this.serialNumber = serialNumber;
            this.name = name;
            this.price = price;
            this.description = description;
            this.shopId = shopId;
        }

        ProductTestDto(BigDecimal price, String description, long shopId) {
            this.price = price;
            this.description = description;
            this.shopId = shopId;
        }
    }
}