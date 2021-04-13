package com.solebysole.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.authentication.dto.SessionRequestData;
import com.solebysole.authentication.service.AuthenticationService;
import com.solebysole.common.errors.LoginFailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("SessionController 클래스")
@WebMvcTest(SessionController.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    private SessionRequestData validSessionRequestData;
    private SessionRequestData inValidSessionRequestData;

    @BeforeEach
    void setUp() {
        validSessionRequestData = SessionRequestData.builder()
                .email("jamie@example.com")
                .password("12345678")
                .build();

        inValidSessionRequestData = SessionRequestData.builder()
                .email(validSessionRequestData.getEmail() + "INVALID")
                .password(validSessionRequestData.getPassword() + "INVALID")
                .build();
    }

    @Nested
    @DisplayName("POST 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("유효한 회원 로그인 정보가 주어진다면")
        class Context_with_valid_user_login_data {
            @BeforeEach
            void setUp() {
                given(authenticationService.login(any(String.class), any(String.class)))
                        .willReturn("a.b.c");
            }

            @DisplayName("액세스 토큰과 상태코드 201 Created 를 응답한다.")
            @Test
            void it_responds_the_access_token_and_status_code_201() throws Exception {
                mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validSessionRequestData)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("accessToken").exists())
                        .andExpect(content().string(containsString(".")));
            }
        }

        @Nested
        @DisplayName("주어진 회원 로그인 정보로 인증에 실패했다면")
        class Context_when_the_authentication_fails {
            @BeforeEach
            void setUp() {
                given(authenticationService.login(any(String.class), any(String.class)))
                        .willThrow(new LoginFailException());
            }

            @DisplayName("에러 메시지와 상태코드 400 Bad Request 를 응답한다.")
            @Test
            void it_responds_the_error_message_and_status_code_400() throws Exception {
                mockMvc.perform(post("/session")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inValidSessionRequestData)))
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("accessToken").doesNotExist())
                        .andExpect(jsonPath("message").exists());
            }
        }
    }

}
