package com.solebysole.cart.application;

import com.solebysole.cart.domain.CartProduct;
import com.solebysole.cart.domain.CartProductRepository;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.cart.dto.CartProductUpdateData;
import com.solebysole.common.errors.CartProductNotFoundException;
import com.solebysole.common.errors.ProductNotFoundException;
import com.solebysole.product.domain.Category;
import com.solebysole.product.domain.Image;
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

import static org.assertj.core.api.Assertions.assertThat;
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

    private final Long existingCartProductId = 1L;
    private final Long notExistingCartProductId = 1000L;

    private User user;

    private CartProductCreateData cartProductCreateData;
    private CartProductUpdateData cartProductUpdateData;

    private Product product1;
    private Product product2;

    private List<CartProduct> cartProducts;
    private CartProduct cartProduct1;
    private CartProduct cartProduct2;

    @BeforeEach
    void setup() {
        cartProductService = new CartProductService(cartproductRepository, productRepository);

        cartProductCreateData = CartProductCreateData.builder()
                .productId(existingProductId)
                .count(3)
                .build();

        cartProductUpdateData = CartProductUpdateData.builder()
                .count(5)
                .build();

        product1 = createProduct("가죽지갑1");
        product2 = createProduct("가죽지갑2");

        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();

        cartProduct1 = CartProduct.builder()
                .product(product1)
                .count(1)
                .build();

        cartProduct2 = CartProduct.builder()
                .product(product2)
                .count(1)
                .build();
    }

    @Nested
    @DisplayName("getCartProducts")
    class Describe_getCartProducts {
        @Nested
        @DisplayName("저장된 장바구니 상품이 여러개 있다면")
        class Context_with_cart_products {
            @BeforeEach
            void setUp() {
                cartProducts = List.of(cartProduct1, cartProduct2);

                given(cartproductRepository.findAllByUserId(user.getId()))
                        .willReturn(cartProducts);
            }

            @Test
            @DisplayName("모든 장바구니 상품 목록을 리턴한다.")
            void it_returns_all_cart_product_list() {
                assertThat(cartProductService.getCartProducts(user)).hasSize(2);
            }
        }

        @Nested
        @DisplayName("저장된 상품이 없다면")
        class Context_without_cart_products {
            @BeforeEach
            void setUp() {
                given(cartproductRepository.findAllByUserId(user.getId()))
                        .willReturn(List.of());
            }

            @Test
            @DisplayName("비어있는 상품 목록을 리턴한다.")
            void it_returns_empty_cart_product_list() {
                assertThat(cartProductService.getCartProducts(user)).hasSize(0);
            }
        }
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
                        .willReturn(Optional.of(product1));

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

    @Nested
    @DisplayName("updateCartProduct")
    class Describe_updateCartProduct {
        @Nested
        @DisplayName("존재하는 장바구니 상품 id가 주어진다면")
        class Context_with_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                given(cartproductRepository.findById(existingCartProductId))
                        .willReturn(Optional.of(cartProduct1));
            }

            @Test
            @DisplayName("장바구니 상품의 개수를 변경한다.")
            void it_update_cart_product_count() {
                cartProductService.updateCartProduct(existingProductId, cartProductUpdateData);

                assertThat(cartProduct1.getCount()).isEqualTo(cartProductUpdateData.getCount());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 장바구니 상품 id가 주어진다면")
        class Context_with_not_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                given(cartproductRepository.findById(notExistingCartProductId))
                        .willReturn(Optional.empty());
            }

            @Test
            @DisplayName("'장바구니 상품을 찾을 수 없습니다.' 라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(CartProductNotFoundException.class,
                        () -> cartProductService.deleteCartProduct(notExistingProductId));
            }
        }
    }

    @Nested
    @DisplayName("deleteCartProduct")
    class Describe_deleteCartProduct {
        @Nested
        @DisplayName("존재하는 장바구니 상품 id가 주어진다면")
        class Context_with_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                given(cartproductRepository.findById(existingCartProductId))
                        .willReturn(Optional.of(cartProduct1));
            }

            @Test
            @DisplayName("장바구니 상품을 삭제한다.")
            void it_create_cart_product() {
                cartProductService.deleteCartProduct(existingProductId);

                verify(cartproductRepository).delete(any(CartProduct.class));
            }
        }

        @Nested
        @DisplayName("존재하지 않는 장바구니 상품 id가 주어진다면")
        class Context_with_not_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                given(cartproductRepository.findById(notExistingCartProductId))
                        .willReturn(Optional.empty());
            }

            @Test
            @DisplayName("'장바구니 상품을 찾을 수 없습니다.' 라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(CartProductNotFoundException.class,
                        () -> cartProductService.deleteCartProduct(notExistingProductId));
            }
        }
    }

    private Product createProduct(String name) {
        Product product = Product.builder()
                .name(name)
                .originalPrice(50000)
                .discountedPrice(40000)
                .description("가죽 지갑입니다.")
                .category(Category.WALLET)
                .build();

        product.addImage(new Image("url1"));

        return product;
    }

}
