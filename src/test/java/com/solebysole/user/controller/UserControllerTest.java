package com.solebysole.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.common.errors.UserEmailDuplicationException;
import com.solebysole.user.application.UserService;
import com.solebysole.user.dto.UserRegisterData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("UserController 클래스")
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private final Long savedId = 1L;

    private UserRegisterData userRegisterData;
    private UserRegisterData invalidUserRegisterData;
    private UserRegisterData duplicateUserRegisterData;

    @BeforeEach
    void setUp() {
        userRegisterData = createUserRegisterData("test@test.com");
        invalidUserRegisterData = createUserRegisterData("");
        duplicateUserRegisterData = createUserRegisterData("duplicate@test.com");
    }

    @Nested
    @DisplayName("POST 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("올바른 회원 정보가 주어진다면")
        class Context_with_valid_user_register_data {
            @BeforeEach
            void setUp() {
                given(userService.registerUser(any(UserRegisterData.class)))
                        .willReturn(savedId);
            }

            @Test
            @DisplayName("상태코드 201 Created 를 응답한다.")
            void it_responds_status_code_201() throws Exception {
                mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterData)))
                        .andExpect(header().string("location", "/api/users/" + savedId))
                        .andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("올바르지 않은 회원 정보가 주어진다면")
        class Context_with_invalid_user_register_data {
            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserRegisterData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("중복된 회원 이메일 주어진다면")
        class Context_with_duplicate_user_email {
            @BeforeEach
            void setUp() {
                given(userService.registerUser(any(UserRegisterData.class)))
                        .willThrow(new UserEmailDuplicationException());
            }

            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicateUserRegisterData)))
                        .andExpect(status().isBadRequest());
            }
        }
    }

    public UserRegisterData createUserRegisterData(String email) {
        return UserRegisterData.builder()
                .email(email)
                .name("test")
                .password("12345678")
                .build();
    }

}
