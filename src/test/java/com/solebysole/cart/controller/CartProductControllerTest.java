package com.solebysole.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.authentication.service.AuthenticationService;
import com.solebysole.cart.application.CartProductService;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.cart.dto.CartProductData;
import com.solebysole.cart.dto.CartProductUpdateData;
import com.solebysole.common.RestDocsConfiguration;
import com.solebysole.common.errors.CartProductNotFoundException;
import com.solebysole.docs.CartDocumentation;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("CartProductController 클래스")
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs
@WebMvcTest(CartProductController.class)
class CartProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartProductService cartProductService;

    @MockBean
    private AuthenticationService authenticationService;

    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private final Long savedId = 1L;
    private final Long existingUserId = 1L;
    private final Long existingProductId = 1L;

    private final Long existingCartProductId = 1L;
    private final Long notExistingCartProductId = 1000L;

    private CartProductCreateData cartProductCreateData;
    private CartProductCreateData invalidCartProductCreateData;

    private CartProductUpdateData cartProductUpdateData;
    private CartProductUpdateData invalidCartProductUpdateData;
    private User user;

    private List<CartProductData> cartProductDataList;
    private CartProductData cartProductData1;
    private CartProductData cartProductData2;

    @WithMockUser
    @BeforeEach
    void setUp() {
        cartProductCreateData = CartProductCreateData.builder()
                .productId(existingProductId)
                .count(3)
                .build();

        invalidCartProductCreateData = CartProductCreateData.builder().build();

        cartProductUpdateData = CartProductUpdateData.builder()
                .count(5)
                .build();

        invalidCartProductUpdateData = CartProductUpdateData.builder()
                .count(0)
                .build();

        user = User.builder()
                .id(existingUserId)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();

        cartProductData1 = createCartProductData(1L, 1L, 3);
        cartProductData2 = createCartProductData(2L, 2L, 1);

        given(authenticationService.parseToken(VALID_TOKEN))
                .willReturn(existingUserId);
        given(authenticationService.loadUserById(existingUserId))
                .willReturn(user);
    }

    @Nested
    @DisplayName("GET 요청은")
    class Describe_GET {
        @Nested
        @DisplayName("저장된 장바구니 상품이 여러개 있다면")
        class Context_with_cart_products {
            @BeforeEach
            void setUp() {
                cartProductDataList = List.of(cartProductData1, cartProductData2);

                given(cartProductService.getCartProducts(user))
                        .willReturn(cartProductDataList);
            }

            @Test
            @DisplayName("모든 장바구니 상품 목록과 상태코드 200 OK 를 응답한다.")
            void it_responds_all_cart_product_data_list() throws Exception {
                mockMvc.perform(get("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN))
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect(status().isOk())
                        .andDo(CartDocumentation.getCartProducts());
            }
        }

        @Nested
        @DisplayName("저장된 장바구니 상품이 없다면")
        class Context_without_cart_products {
            @BeforeEach
            void setUp() {
                cartProductDataList = List.of();

                given(cartProductService.getCartProducts(user))
                        .willReturn(cartProductDataList);
            }

            @Test
            @DisplayName("비어있는 상품 목록과 상태코드 200 OK 를 응답한다.")
            void it_responds_empty_cart_product_data_list() throws Exception {
                mockMvc.perform(get("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN))
                        .andExpect(jsonPath("$", hasSize(0)))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("알수 없는 사용자가 주어진다면")
        class Context_with_anonymous_user {
            @Test
            @DisplayName("상태코드 401 Unauthorized 를 응답한다.")
            void it_responds_status_code_401() throws Exception {
                mockMvc.perform(get("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnauthorized());
            }
        }
    }

    @Nested
    @DisplayName("POST 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("올바른 장바구니 상품 정보가 주어진다면")
        class Context_with_cart_product_create_data {
            @BeforeEach
            void setUp() {
                given(cartProductService.crateCartProduct(any(User.class), any(CartProductCreateData.class)))
                        .willReturn(savedId);
            }

            @Test
            @DisplayName("상태코드 201 Created 를 응답한다.")
            void it_responds_status_code_201() throws Exception {
                mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(cartProductCreateData)))
                        .andExpect(status().isCreated())
                        .andDo(CartDocumentation.createCartProduct());
            }
        }

        @Nested
        @DisplayName("올바르지 않은 상품 정보가 주어진다면")
        class Context_with_invalid_cart_product_data {
            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(invalidCartProductCreateData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("알수 없는 사용자가 주어진다면")
        class Context_with_anonymous_user {
            @Test
            @DisplayName("상태코드 401 Unauthorized 를 응답한다.")
            void it_responds_status_code_401() throws Exception {
                mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartProductCreateData)))
                        .andExpect(status().isUnauthorized());
            }
        }
    }

    @Nested
    @DisplayName("PATCH 요청은")
    class Describe_PATCH {
        @Nested
        @DisplayName("존재하는 장바구니 상품 id가 주어진다면")
        class Context_with_existing_cart_product_id {
            @Test
            @DisplayName("상태코드 200 OK 를 응답한다.")
            void it_responds_status_code_200() throws Exception {
                mockMvc.perform(patch("/api/cart/{id}", existingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(cartProductUpdateData)))
                        .andExpect(status().isOk())
                        .andDo(CartDocumentation.updateCartProduct());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 장바구니 상품 id가 주어진다면")
        class Context_with_not_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                willThrow(new CartProductNotFoundException())
                        .given(cartProductService)
                        .updateCartProduct(any(Long.class), any(CartProductUpdateData.class));
            }

            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(patch("/api/cart/{id}", notExistingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(cartProductUpdateData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("올바르지 않은 상품 수정 정보가 주어진다면")
        class Context_with_invalid_cart_product_update_data {
            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(patch("/api/cart/{id}", existingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(invalidCartProductUpdateData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("알수 없는 사용자가 주어진다면")
        class Context_with_anonymous_user {
            @Test
            @DisplayName("상태코드 401 Unauthorized 를 응답한다.")
            void it_responds_status_code_401() throws Exception {
                mockMvc.perform(patch("/api/cart/{id}", existingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartProductUpdateData)))
                        .andExpect(status().isUnauthorized());
            }
        }
    }

    @Nested
    @DisplayName("DELETE 요청은")
    class Describe_DELETE {
        @Nested
        @DisplayName("존재하는 장바구니 상품 id가 주어진다면")
        class Context_with_existing_cart_product_id {
            @Test
            @DisplayName("상태코드 204 No Content 를 응답한다.")
            void it_responds_status_code_204() throws Exception {
                mockMvc.perform(delete("/api/cart/{id}", existingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN))
                        .andExpect(status().isNoContent())
                        .andDo(CartDocumentation.deleteCartProduct());
            }
        }

        @Nested
        @DisplayName("존재하지 않는 장바구니 상품 id가 주어진다면")
        class Context_with_not_existing_cart_product_id {
            @BeforeEach
            void setUp() {
                willThrow(new CartProductNotFoundException())
                        .given(cartProductService).deleteCartProduct(notExistingCartProductId);
            }

            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(delete("/api/cart/{id}", notExistingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("알수 없는 사용자가 주어진다면")
        class Context_with_anonymous_user {
            @Test
            @DisplayName("상태코드 401 Unauthorized 를 응답한다.")
            void it_responds_status_code_401() throws Exception {
                mockMvc.perform(delete("/api/cart/{id}", existingCartProductId)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isUnauthorized());
            }
        }
    }

    private CartProductData createCartProductData(Long id, Long productId, int count) {
        return CartProductData.builder()
                .id(id)
                .productId(productId)
                .name("가죽지갑")
                .originalPrice(50000)
                .discountedPrice(40000)
                .imageUrl("url1")
                .count(count)
                .build();
    }

}
