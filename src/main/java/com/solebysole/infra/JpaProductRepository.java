package com.solebysole.infra;

import com.solebysole.domain.Product;
import com.solebysole.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA 상품 저장소.
 */
public interface JpaProductRepository
        extends ProductRepository, JpaRepository<Product, Long> {

    List<Product> findAll();

    Product save(Product product);

    boolean existsByName(String name);

}
