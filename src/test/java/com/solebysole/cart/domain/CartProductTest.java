package com.solebysole.cart.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartProductTest {

    @DisplayName("changeCount 메소드는 주어진 개수로 현재 장바구니 상품 개수를 갱신합니다.")
    @Test
    void changeCount() {
        int count = 3;
        int nextCount = 5;
        CartProduct cartProduct = CartProduct.builder()
                .count(count)
                .build();

        cartProduct.changeCount(nextCount);
        assertThat(cartProduct.getCount()).isEqualTo(nextCount);
    }

}
