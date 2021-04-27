package com.solebysole.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 주문 상품 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderProductData {

    @NotNull
    private Long productId;

    @Min(value = 1)
    private int count;

    @Builder
    private OrderProductData(Long productId, int count) {
        this.productId = productId;
        this.count = count;
    }

}
