package com.solebysole.user.application;

import com.solebysole.common.errors.UserEmailDuplicationException;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import com.solebysole.user.dto.UserRegisterData;
import com.solebysole.user.dto.UserUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
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

    private User user;
    private UserUpdateData userUpdateData;

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

        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .password("abcd1234")
                .role(Role.ROLE_USER)
                .build();

        userUpdateData = UserUpdateData.builder()
                .name("newName")
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

    @Nested
    @DisplayName("updateUser")
    class Describe_updateUser {
        @Nested
        @DisplayName("회원과 회원 수정 정보가 주어진다면")
        class Context_with_valid_user_and_user_update_data {
            @Test
            @DisplayName("회원을 변경한다.")
            void it_change_user() {
                userService.updateUser(user, userUpdateData);

                verify(userRepository).save(any(User.class));
            }
        }
    }

    @Nested
    @DisplayName("deleteUser")
    class Describe_deleteUser {
        @Nested
        @DisplayName("회원이 주어진다면")
        class Context_with_user {
            @Test
            @DisplayName("회원을 삭제합니다.")
            void it_delete_user() {
                userService.deleteUser(user);

                assertThat(user.isDeleted()).isTrue();

                verify(userRepository).save(any(User.class));
            }
        }
    }

}
