package com.solebysole.domain;

/**
 * 상품 저장소.
 */
public interface ProductRepository {

    Product save(Product product);

    boolean existsByName(String name);

}
