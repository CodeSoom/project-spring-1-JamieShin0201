package com.solebysole.cart.domain;

import java.util.List;

/**
 * 장바구니 상품 저장소.
 */
public interface CartProductRepository {

    List<CartProduct> findAllByUserId(Long userId);

    CartProduct save(CartProduct cartProduct);

}
