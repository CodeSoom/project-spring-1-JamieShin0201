package com.solebysole.order.dto;

import com.solebysole.order.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 주문 생성 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateData {

    @NotNull
    private List<OrderProductData> orderProductDataList;

    @NotNull
    private Address address;

    @Builder
    private OrderCreateData(List<OrderProductData> orderProductDataList, Address address) {
        this.orderProductDataList = orderProductDataList;
        this.address = address;
    }

}
