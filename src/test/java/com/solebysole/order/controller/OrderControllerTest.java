package com.solebysole.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.authentication.service.AuthenticationService;
import com.solebysole.order.application.OrderService;
import com.solebysole.order.domain.Address;
import com.solebysole.order.dto.OrderCreateData;
import com.solebysole.order.dto.OrderProductData;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("OrderController 클래스")
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @MockBean
    private AuthenticationService authenticationService;

    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";

    private final Long savedId = 1L;
    private final Long existingUserId = 1L;

    private User user;

    private OrderCreateData orderCreateData;
    private OrderCreateData invalidOrderCreateData;
    private Address address;

    private OrderProductData orderProductData1;
    private OrderProductData orderProductData2;


    @WithMockUser
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("1234abcd")
                .role(Role.ROLE_USER)
                .build();

        orderProductData1 = OrderProductData.builder()
                .productId(1L)
                .count(3)
                .build();

        orderProductData2 = OrderProductData.builder()
                .productId(2L)
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

        invalidOrderCreateData = OrderCreateData.builder().build();

        given(authenticationService.parseToken(VALID_TOKEN))
                .willReturn(existingUserId);
        given(authenticationService.loadUserById(existingUserId))
                .willReturn(user);
    }

    @Nested
    @DisplayName("POST 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("올바른 주문 정보가 주어진다면")
        class Context_with_valid_order_create_data {
            @BeforeEach
            void setUp() {
                given(orderService.order(any(User.class), any(OrderCreateData.class)))
                        .willReturn(savedId);
            }

            @Test
            @DisplayName("상태코드 201 Created 를 응답한다.")
            void it_responds_status_code_201() throws Exception {
                mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(orderCreateData)))
                        .andExpect(header().string("location", "/api/order/" + savedId))
                        .andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("올바르지 않은 주문 정보가 주어진다면")
        class Context_with_invalid_order_create_data {
            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + VALID_TOKEN)
                        .content(objectMapper.writeValueAsString(invalidOrderCreateData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("알수 없는 사용자가 주어진다면")
        class Context_with_anonymous_user {
            @Test
            @DisplayName("상태코드 401 Unauthorized 를 응답한다.")
            void it_responds_status_code_401() throws Exception {
                mockMvc.perform(post("/api/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreateData)))
                        .andExpect(status().isUnauthorized());
            }
        }
    }

}
