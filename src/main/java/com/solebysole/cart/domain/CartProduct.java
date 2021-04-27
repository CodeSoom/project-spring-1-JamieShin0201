package com.solebysole.cart.domain;

import com.solebysole.product.domain.Product;
import com.solebysole.user.domain.User;
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
 * 장바구니 상품.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CartProduct {

    @Id
    @GeneratedValue
    @Column(name = "cart_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int count;

    @Builder
    private CartProduct(Long id, User user, Product product, int count) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.count = count;
    }

    /**
     * 주어진 개수로 장바구니 상품의 개수를 갱신합니다.
     *
     * @param count 개수
     */
    public void changeCount(int count) {
        this.count = count;
    }

}
