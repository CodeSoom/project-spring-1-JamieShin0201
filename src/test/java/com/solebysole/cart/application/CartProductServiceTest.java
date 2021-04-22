package com.solebysole.cart.application;

import com.solebysole.cart.domain.CartProduct;
import com.solebysole.cart.domain.CartProductRepository;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.common.errors.ProductNotFoundException;
import com.solebysole.product.domain.Category;
import com.solebysole.product.domain.Product;
import com.solebysole.product.domain.ProductRepository;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("CartProductService 클래스")
class CartProductServiceTest {

    private CartProductService cartProductService;

    private CartProductRepository cartproductRepository = mock(CartProductRepository.class);
    private ProductRepository productRepository = mock(ProductRepository.class);

    private final Long existingProductId = 1L;
    private final Long notExistingProductId = 1000L;

    private CartProductCreateData cartProductCreateData;
    private Product product;
    private User user;

    @BeforeEach
    void setup() {
        cartProductService = new CartProductService(cartproductRepository, productRepository);

        cartProductCreateData = CartProductCreateData.builder()
                .productId(existingProductId)
                .count(3)
                .build();

        product = Product.builder()
                .name("가죽 지갑")
                .originalPrice(50000)
                .discountedPrice(40000)
                .description("가죽 지갑입니다.")
                .category(Category.WALLET)
                .build();

        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();
    }

    @Nested
    @DisplayName("createCartProduct")
    class Describe_createCartProduct {
        @Nested
        @DisplayName("올바른 장바구니 상품 정보가 주어진다면")
        class Context_with_cart_product {
            @BeforeEach
            void setUp() {
                given(productRepository.findById(existingProductId))
                        .willReturn(Optional.of(product));

                given(cartproductRepository.save(any(CartProduct.class)))
                        .will(invocation -> invocation.<CartProduct>getArgument(0));
            }

            @Test
            @DisplayName("장바구니 상품을 생성한다.")
            void it_create_cart_product() {
                cartProductService.crateCartProduct(user, cartProductCreateData);

                verify(cartproductRepository).save(any(CartProduct.class));
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
                        () -> cartProductService.crateCartProduct(user, cartProductCreateData));
            }
        }
    }

}
