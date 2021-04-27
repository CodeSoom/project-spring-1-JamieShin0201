package com.solebysole.order.application;

import com.solebysole.common.errors.ProductNotFoundException;
import com.solebysole.order.domain.Delivery;
import com.solebysole.order.domain.DeliveryStatus;
import com.solebysole.order.domain.Order;
import com.solebysole.order.domain.OrderProduct;
import com.solebysole.order.domain.OrderRepository;
import com.solebysole.order.dto.OrderCreateData;
import com.solebysole.product.domain.Product;
import com.solebysole.product.domain.ProductRepository;
import com.solebysole.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 주문과 관련된 비즈니스 로직을 담당합니다.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    /**
     * 주어진 주문 정보로 주문합니다.
     *
     * @param user 현재 회원
     * @param orderCreateData 주문 정보
     * @return 주문 식별자
     * @throws ProductNotFoundException 상품을 찾을 수 없는 경우
     */
    @Transactional
    public Long order(User user, OrderCreateData orderCreateData)
            throws ProductNotFoundException {
        Delivery delivery = Delivery.builder()
                .address(orderCreateData.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        List<OrderProduct> orderProducts = orderCreateData.getOrderProductDataList()
                .stream()
                .map((orderProductData) -> {
                    Long productId = orderProductData.getProductId();
                    Product product = findProduct(productId);

                    return OrderProduct.create(product, orderProductData.getCount());
                })
                .collect(Collectors.toList());

        Order order = Order.create(user, delivery, orderProducts);
        orderRepository.save(order);

        return order.getId();
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
