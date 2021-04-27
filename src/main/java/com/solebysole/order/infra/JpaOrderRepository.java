package com.solebysole.order.infra;

import com.solebysole.order.domain.Order;
import com.solebysole.order.domain.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA 주문 저장소.
 */
public interface JpaOrderRepository
        extends OrderRepository, JpaRepository<Order, Long> {

    Order save(Order order);

}
