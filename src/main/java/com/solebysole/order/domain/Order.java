package com.solebysole.order.domain;

import com.solebysole.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime orderDate;

    private LocalDateTime cancelDate;

    @Builder
    private Order(Long id, User user, Delivery delivery, List<OrderProduct> orderProducts,
                  OrderStatus status, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.delivery = delivery;
        this.orderProducts = orderProducts;
        this.status = status;
        this.orderDate = orderDate;
    }

    /**
     * 주어진 회원, 배송, 주문 상품 목록으로 주문을 생성합니다.
     *
     * @param user 회원
     * @param delivery 배송
     * @param orderProducts 주문 상품 목록
     * @return 주문
     */
    public static Order create(User user, Delivery delivery, List<OrderProduct> orderProducts) {
        Order order = Order.builder()
                .user(user)
                .delivery(delivery)
                .orderProducts(orderProducts)
                .status(OrderStatus.ORDER)
                .orderDate(LocalDateTime.now())
                .build();

        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(order);
        }

        return order;
    }

}
