package com.solebysole.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("User 클래스")
class UserTest {

    private PasswordEncoder passwordEncoder;

    private final String validPassword = "test1234";
    private final String invalidPassword = validPassword + "INVALID";
    private final String newPassword = "newPassword";
    private final String newName = "newName";

    private User user;
    private User updatingUser;
    private User deletedUser;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();

        user = User.builder().build();
        user.changePassword(validPassword, passwordEncoder);

        updatingUser = User.builder()
                .name(newName)
                .build();

        deletedUser = User.builder()
                .deleted(true)
                .build();
        deletedUser.changePassword(validPassword, passwordEncoder);
    }

    @Nested
    @DisplayName("changeWith")
    class Describe_changeWith {
        @Nested
        @DisplayName("변경할 회원 정보가 주어진다면")
        class Context_with_updating_user {
            @Test
            @DisplayName("회원을 변경한다.")
            void it_change_user() {
                user.changeWith(updatingUser);

                assertThat(user.getName()).isEqualTo(newName);
            }
        }
    }

    @Nested
    @DisplayName("changePassword")
    class Describe_changePassword {
        @Nested
        @DisplayName("변경할 비밀번호가 주어진다면")
        class Context_with_password {
            @Test
            @DisplayName("비밀번호를 암호화하고 변경한다.")
            void it_encode_password_and_change() {
                user.changePassword(newPassword, passwordEncoder);

                assertThat(user.getPassword()).isNotEqualTo(newPassword);
                assertThat(user.getPassword()).isNotEmpty();
            }
        }
    }

    @Nested
    @DisplayName("authenticate")
    class Describe_authenticate {
        @Nested
        @DisplayName("비밀번호가 일치하고 삭제된 회원이 아니라면")
        class Context_with_valid_user {
            @Test
            @DisplayName("True 를 리턴한다.")
            void it_returns_true() {
                assertThat(user.authenticate(validPassword, passwordEncoder)).isTrue();
            }
        }

        @Nested
        @DisplayName("비밀번호가 다르다면")
        class Context_with_different_password {
            @Test
            @DisplayName("False 를 리턴한다.")
            void it_returns_false() {
                assertThat(user.authenticate(invalidPassword, passwordEncoder)).isFalse();
            }
        }

        @Nested
        @DisplayName("삭제된 회원이 주어진다면")
        class Context_with_deleted_user {
            @Test
            @DisplayName("False 를 리턴한다.")
            void it_returns_false() {
                assertThat(deletedUser.authenticate(validPassword, passwordEncoder)).isFalse();
            }
        }
    }

}
