package com.solebysole.order.application;

import com.solebysole.common.errors.ProductNotFoundException;
import com.solebysole.order.domain.Address;
import com.solebysole.order.domain.Order;
import com.solebysole.order.domain.OrderRepository;
import com.solebysole.order.dto.OrderCreateData;
import com.solebysole.order.dto.OrderProductData;
import com.solebysole.product.domain.Product;
import com.solebysole.product.domain.ProductRepository;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("OrderService 클래스")
class OrderServiceTest {

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private ProductRepository productRepository = mock(ProductRepository.class);

    private OrderService orderService;

    private User user;

    private OrderCreateData orderCreateData;
    private Address address;

    private OrderProductData orderProductData1;
    private OrderProductData orderProductData2;

    private final Long existingProductId1 = 1L;
    private final Long existingProductId2 = 2L;
    private final Long notExistingProductId = 1000L;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, productRepository);

        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();

        orderProductData1 = OrderProductData.builder()
                .productId(existingProductId1)
                .count(3)
                .build();

        orderProductData2 = OrderProductData.builder()
                .productId(existingProductId2)
                .count(5)
                .build();

        address = Address.builder()
                .base("서울시 강남구")
                .detail("아파트")
                .zipcode("111222")
                .build();

        orderCreateData = OrderCreateData.builder()
                .orderProductDataList(List.of(orderProductData1, orderProductData2))
                .address(address)
                .build();

        product1 = Product.builder().build();
        product2 = Product.builder().build();
    }

    @Nested
    @DisplayName("order")
    class Describe_order {
        @Nested
        @DisplayName("올바른 주문 정보가 주어진다면")
        class Context_with_valid_order_create_data {
            @BeforeEach
            void setUp() {
                given(productRepository.findById(existingProductId1))
                        .willReturn(Optional.of(product1));

                given(productRepository.findById(existingProductId2))
                        .willReturn(Optional.of(product2));

                given(orderRepository.save(any(Order.class)))
                        .will(invocation -> invocation.<Product>getArgument(0));
            }

            @Test
            @DisplayName("주문한다.")
            void it_order() {
                orderService.order(user, orderCreateData);

                verify(orderRepository).save(any(Order.class));
            }
        }

        @Nested
        @DisplayName("존재하지 않는 상품 id가 주어진다면")
        class Context_with_not_existing_product_id {
            @BeforeEach
            void setUp() {
                given(productRepository.findById(notExistingProductId))
                        .willReturn(Optional.empty());
            }

            @Test
            @DisplayName("'상품을 찾을 수 없습니다.' 라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(ProductNotFoundException.class,
                        () -> orderService.order(user, orderCreateData));
            }
        }
    }
}
