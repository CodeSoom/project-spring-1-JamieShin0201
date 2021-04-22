package com.solebysole.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.authentication.service.AuthenticationService;
import com.solebysole.cart.application.CartProductService;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("CartProductController 클래스")
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

    private CartProductCreateData cartProductCreateData;
    private CartProductCreateData invalidCartProductCreateData;
    private User user;

    @WithMockUser
    @BeforeEach
    void setUp() {
        cartProductCreateData = CartProductCreateData.builder()
                .productId(existingProductId)
                .count(3)
                .build();

        invalidCartProductCreateData = CartProductCreateData.builder().build();

        user = User.builder()
                .id(existingUserId)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();

        given(authenticationService.parseToken(VALID_TOKEN))
                .willReturn(1L);
        given(authenticationService.loadUserById(existingUserId))
                .willReturn(user);
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
                        .andExpect(status().isCreated());
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

}
