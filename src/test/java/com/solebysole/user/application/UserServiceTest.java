package com.solebysole.user.application;

import com.solebysole.common.errors.UserEmailDuplicationException;
import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import com.solebysole.user.dto.UserRegisterData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("UserService 클래스")
class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository = mock(UserRepository.class);

    private UserRegisterData userRegisterData;
    private UserRegisterData duplicateUserRegisterData;

    @BeforeEach
    void setUp() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userService = new UserService(userRepository, passwordEncoder);


        userRegisterData = UserRegisterData.builder()
                .email("test@test.com")
                .name("test")
                .password("12345678")
                .build();

        duplicateUserRegisterData = UserRegisterData.builder()
                .email("duplicate@test.com")
                .name("test")
                .password("12345678")
                .build();
    }

    @Nested
    @DisplayName("registerUser")
    class Describe_registerUser {
        @Nested
        @DisplayName("올바른 회원 정보가 주어진다면")
        class Context_with_a_valid_user_register_data {
            @BeforeEach
            void setUp() {
                given(userRepository.save(any(User.class)))
                        .will(invocation -> invocation.<User>getArgument(0));
            }

            @Test
            @DisplayName("회원이 생성된다.")
            void it_create_user() {
                userService.registerUser(userRegisterData);

                verify(userRepository).save(any(User.class));
            }
        }

        @Nested
        @DisplayName("중복된 이메일이 주어진다면")
        class Context_with_the_duplicate_email {
            @BeforeEach
            void setUp() {
                given(userRepository.existsByEmail(duplicateUserRegisterData.getEmail()))
                        .willReturn(true);
            }

            @Test
            @DisplayName("비어있는 상품 목록을 리턴한다.")
            void it_throws_exception() {
                assertThrows(UserEmailDuplicationException.class,
                        () -> userService.registerUser(duplicateUserRegisterData));
            }
        }
    }

}
