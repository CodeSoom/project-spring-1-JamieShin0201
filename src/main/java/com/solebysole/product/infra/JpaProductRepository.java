package com.solebysole.product.infra;

import com.solebysole.product.domain.Product;
import com.solebysole.product.domain.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA 상품 저장소.
 */
public interface JpaProductRepository
        extends ProductRepository, JpaRepository<Product, Long> {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    boolean existsByName(String name);

}
