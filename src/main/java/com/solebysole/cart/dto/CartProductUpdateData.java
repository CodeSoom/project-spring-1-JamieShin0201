package com.solebysole.cart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 장바구니 상품 변경 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductUpdateData {

    @Max(value = 99)
    @Min(value = 1)
    private int count;

    @Builder
    private CartProductUpdateData(int count) {
        this.count = count;
    }

}

