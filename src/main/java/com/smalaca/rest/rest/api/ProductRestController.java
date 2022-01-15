package com.smalaca.rest.rest.api;

import com.smalaca.rest.domain.product.Product;
import com.smalaca.rest.domain.product.ProductDto;
import com.smalaca.rest.domain.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductRepository repository;

    public ProductRestController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProductDto> findAll(@RequestParam MultiValueMap<String, String> params) {
        if (params.isEmpty()) {
            return StreamSupport.stream(repository.findAll().spliterator(), false)
                    .map(Product::asDto)
                    .collect(toList());
        } else if (params.containsKey("shopIds")) {
            List<Long> shopIds = params.get("shopIds").stream().map(Long::parseLong).collect(toList());
            return repository.findAllByShopIdIn(shopIds).stream()
                    .map(Product::asDto)
                    .collect(toList());
        } else {
            return repository.findAllByNameOrSerialNumber(params.getFirst("name"), params.getFirst("serialNumber")).stream()
                    .map(Product::asDto)
                    .collect(toList());
        }
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProductDto dto) {
        if (repository.existsBySerialNumber(dto.getSerialNumber())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Product product = new Product(dto.getSerialNumber(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getShopId());
        Long id = repository.save(product).getId();
        return new ResponseEntity(id, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        if (repository.existsById(id)) {
            ProductDto productDto = repository.findById(id).get().asDto();
            return ok(productDto);
        } else {
            return notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ok().build();
        } else {
            return notFound().build();
        }
    }

    @PutMapping("{id}")
    public void modify(@PathVariable Long id, @RequestBody ProductDto dto) {
        Product product = repository.findById(id).get();
        product.update(dto);

        repository.save(product);
    }
}
