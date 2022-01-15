package com.smalaca.rest.rest.api;

import com.smalaca.rest.domain.product.Product;
import com.smalaca.rest.domain.product.ProductDto;
import com.smalaca.rest.domain.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductRepository repository;

    public ProductRestController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(Product::asDto)
                .collect(toList());
    }

    @PostMapping
    public Long create(@RequestBody ProductDto dto) {
        Product product = new Product(dto.getSerialNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getShopId());
        return repository.save(product).getId();
    }
}
