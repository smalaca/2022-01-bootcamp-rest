package com.smalaca.rest.domain.product;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity
@NoArgsConstructor(access = PRIVATE)
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String serialNumber;
    private String name;
    private BigDecimal price;
    private String description;
    private Long shopId;
    private String creationHost;
    private String creationUser;

    public Product(
            String serialNumber, String name, BigDecimal price, String description, long shopId,
            String creationHost, String creationUser) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.price = price;
        this.description = description;
        this.shopId = shopId;
        this.creationHost = creationHost;
        this.creationUser = creationUser;
    }

    public ProductDto asDto() {
        return new ProductDto(id, serialNumber, name, price, description, shopId, creationHost, creationUser);
    }

    public Long getId() {
        return id;
    }

    public void update(ProductDto dto) {
        price = dto.getPrice();
        description = dto.getDescription();
        shopId = dto.getShopId();
    }
}
