package com.solebysole.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @DisplayName("changePassword 메소드는 변경할 비밀번호가 주어진다면 비밀번호를 암호화하고 변경한다.")
    @Test
    void changePassword() {
        User user = User.builder().build();

        user.changePassword("TEST", passwordEncoder);

        assertThat(user.getPassword()).isNotEmpty();
        assertThat(user.getPassword()).isNotEqualTo("TEST");
    }

}
