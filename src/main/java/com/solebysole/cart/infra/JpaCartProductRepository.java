package com.solebysole.cart.infra;

import com.solebysole.cart.domain.CartProduct;
import com.solebysole.cart.domain.CartProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA 장바구니 상품 저장소.
 */
public interface JpaCartProductRepository
        extends CartProductRepository, JpaRepository<CartProduct, Long> {

    CartProduct save(CartProduct cartProduct);

}
