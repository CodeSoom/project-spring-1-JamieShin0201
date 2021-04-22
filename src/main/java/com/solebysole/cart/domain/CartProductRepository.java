package com.solebysole.cart.domain;

/**
 * 장바구니 상품 저장소.
 */
public interface CartProductRepository {

    CartProduct save(CartProduct cartProduct);

}
