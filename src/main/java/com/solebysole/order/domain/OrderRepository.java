package com.solebysole.order.domain;

/**
 * 주문 저장소.
 */
public interface OrderRepository {

    Order save(Order order);

}
