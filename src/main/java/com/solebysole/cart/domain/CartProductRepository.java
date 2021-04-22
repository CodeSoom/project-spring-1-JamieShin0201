package com.solebysole.cart.domain;

import java.util.List;
import java.util.Optional;

/**
 * 장바구니 상품 저장소.
 */
public interface CartProductRepository {

    List<CartProduct> findAllByUserId(Long userId);

    Optional<CartProduct> findById(Long id);

    CartProduct save(CartProduct cartProduct);

    void delete(CartProduct cartProduct);

}
