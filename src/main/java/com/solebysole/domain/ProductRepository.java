package com.solebysole.domain;

import java.util.List;

/**
 * 상품 저장소.
 */
public interface ProductRepository {

    List<Product> findAll();

    Product save(Product product);

    boolean existsByName(String name);

}
