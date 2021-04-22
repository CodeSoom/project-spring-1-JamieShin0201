package com.solebysole.cart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 장바구니 상품 생성 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductCreateData {

    @NotNull
    private Long productId;

    @Min(value = 1)
    private int count;

    @Builder
    private CartProductCreateData(Long productId, int count) {
        this.productId = productId;
        this.count = count;
    }

}

