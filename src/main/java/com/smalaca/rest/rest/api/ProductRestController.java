package com.smalaca.rest.rest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    @GetMapping
    public List<ProductDto> findAll() {
        return Arrays.asList(
                new ProductDto(1L, UUID.randomUUID().toString(), "Coffee", BigDecimal.valueOf(12.34), "The best product of the world", 1L),
                new ProductDto(2L, UUID.randomUUID().toString(), "Tea", BigDecimal.valueOf(10.01), "Worth to drink from time to time", 1L),
                new ProductDto(3L, UUID.randomUUID().toString(), "Pillow", BigDecimal.valueOf(12.34), "You need it when you want to sleep", 2L),
                new ProductDto(4L, UUID.randomUUID().toString(), "Light saber", BigDecimal.valueOf(12.34), "You have to have it", 3L)
        );
    }
}
