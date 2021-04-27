package com.solebysole.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solebysole.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 주문 상품.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    @Builder
    private OrderProduct(Long id, Product product,
                         Order order, int orderPrice, int count) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    /**
     * 주어진 상품과 개수로 주문 상품을 생성합니다.
     *
     * @param product 상품
     * @param count 개수
     * @return 주문 상품
     */
    public static OrderProduct create(Product product, int count) {
        return OrderProduct.builder()
                .product(product)
                .orderPrice(product.getDiscountedPrice())
                .count(count)
                .build();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
