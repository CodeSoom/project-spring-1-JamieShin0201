package com.solebysole.authentication.service;

import com.solebysole.authentication.utils.JwtUtil;
import com.solebysole.common.errors.InvalidTokenException;
import com.solebysole.common.errors.LoginFailException;
import com.solebysole.common.errors.UserNotFoundException;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("AuthenticationService 클래스")
class AuthenticationServiceTest {

    private static final String SECRET = "12345678901234567890123456789012";

    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final String INVALID_TOKEN = VALID_TOKEN + "INVALID";

    private AuthenticationService authenticationService;

    private UserRepository userRepository = mock(UserRepository.class);

    private User user;

    private final Long existingId = 1L;
    private final Long notExistingId = 1000L;
    private final Long parsedUserId = 1L;

    private final String validEmail = "tester@example.com";
    private final String validPassword = "test1234";

    private final String wrongEmail = validEmail + "WRONG";
    private final String wrongPassword = validPassword + "WRONG";

    private final Role role = Role.ROLE_USER;

    @BeforeEach
    void setUp() {
        JwtUtil jwtUtil = new JwtUtil(SECRET);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        authenticationService = new AuthenticationService(
                userRepository, jwtUtil, passwordEncoder);

        user = User.builder()
                .id(existingId)
                .email(validEmail)
                .password(validPassword)
                .role(role)
                .build();
        user.changePassword(validPassword, passwordEncoder);
    }

    @Nested
    @DisplayName("login")
    class Describe_login {
        @Nested
        @DisplayName("올바른 이메일, 비밀번호가 주어진다면")
        class Context_with_right_email_and_password {
            @BeforeEach
            void setUp() {
                given(userRepository.findByEmail(validEmail))
                        .willReturn(Optional.of(user));
            }

            @Test
            @DisplayName("액세스 토큰을 리턴한다.")
            void it_returns_access_token() {
                String accessToken = authenticationService.login(
                        validEmail, validPassword);

                assertThat(accessToken).isEqualTo(VALID_TOKEN);
            }
        }

        @Nested
        @DisplayName("잘못된 이메일이 주어진다면")
        class Context_with_wrong_email {
            @BeforeEach
            void setUp() {
                given(userRepository.findByEmail(wrongEmail))
                        .willThrow(new LoginFailException());
            }

            @Test
            @DisplayName("'로그인에 실패하였습니다.'라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(LoginFailException.class,
                        () -> authenticationService.login(wrongEmail, validPassword));
            }
        }

        @Nested
        @DisplayName("잘못된 비밀번호가 주어진다면")
        class Context_with_wrong_password {
            @BeforeEach
            void setUp() {
                given(userRepository.findByEmail(validEmail))
                        .willReturn(Optional.of(user));
            }

            @Test
            @DisplayName("'로그인에 실패하였습니다.'라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(LoginFailException.class,
                        () -> authenticationService.login(validEmail, wrongPassword));
            }
        }
    }

    @Nested
    @DisplayName("parseToken")
    class Describe_parseToken {
        @Nested
        @DisplayName("유효한 토큰이 주어진다면")
        class Context_with_a_valid_token {
            @DisplayName("파싱된 값을 리턴한다.")
            @Test
            void it_returns_the_parsed_value() {
                final Long userId = authenticationService.parseToken(VALID_TOKEN);

                assertThat(userId).isEqualTo(parsedUserId);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 토큰이 주어진다면")
        class Context_with_a_invalid_token {
            @DisplayName("'유효하지 않은 토큰입니다.' 라는 예외를 던진다.")
            @Test
            void it_throws_invalid_token_exception() {
                assertThrows(InvalidTokenException.class,
                        () -> authenticationService.parseToken(INVALID_TOKEN));
            }
        }
    }

    @Nested
    @DisplayName("getUserRole")
    class Describe_getUserRole {
        @Nested
        @DisplayName("주어진 id에 해당하는 회원이 존재한다면")
        class Context_with_a_existing_id {
            @BeforeEach
            void setUp() {
                given(userRepository.findById(existingId))
                        .willReturn(Optional.of(user));
            }

            @DisplayName("권한을 리턴한다.")
            @Test
            void it_returns_role() {
                assertThat(user.getRole()).isEqualTo(role);
            }
        }

        @Nested
        @DisplayName("주어진 id에 해당하는 회원이 존재하지 않는다면")
        class Context_with_not_existing_id {
            @BeforeEach
            void setUp() {
                given(userRepository.findById(notExistingId))
                        .willReturn(Optional.empty());
            }

            @DisplayName("'회원을 찾을 수 없습니다.' 라는 예외를 던진다.")
            @Test
            void it_throws_user_not_found_exception() {
                assertThrows(UserNotFoundException.class,
                        () -> authenticationService.getUserRoleById(notExistingId));
            }
        }
    }

}
