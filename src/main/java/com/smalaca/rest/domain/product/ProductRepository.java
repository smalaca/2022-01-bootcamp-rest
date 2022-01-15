package com.smalaca.rest.domain.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByNameOrSerialNumber(String name, String serialNumber);

    List<Product> findAllByShopIdIn(List<Long> shopIds);
}
