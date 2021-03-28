package com.solebysole.infra;

import com.solebysole.domain.Product;
import com.solebysole.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA 상품 저장소.
 */
public interface JpaProductRepository
        extends ProductRepository, JpaRepository<Product, Long> {

    Product save(Product product);

    boolean existsByName(String name);

}
