package com.solebysole.authentication.utils;

import com.solebysole.common.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789012";
    
    private static final String VALID_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjF9.ZZ3CUl0jxeLGvQ1Js5nG2Ty5qGTlqai5ubDMXZOdaDk";
    private static final String INVALID_TOKEN = VALID_TOKEN + "INVALID";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(SECRET);
    }

    @DisplayName("encode 메소드는 주어진 회원 id를 이용해 부호화한 JWT 토큰 문자열을 리턴합니다.")
    @Test
    void encode() {
        String token = jwtUtil.encode(1L);

        assertThat(token).isEqualTo(VALID_TOKEN);
    }

    @DisplayName("decode 메소드는 유효한 토큰이 주어진다면 복호화된 정보 조각을 리턴합니다.")
    @Test
    void decodeWithValidToken() {
        Claims claims = jwtUtil.decode(VALID_TOKEN);

        assertThat(claims.get("userId", Long.class)).isEqualTo(1L);
    }

    @DisplayName("decode 메소드는 유효하지 않은 토큰이 주어진다면 '유효하지 않은 토큰입니다.' 라는 예외가 발생합니다.")
    @Test
    void decodeWithInvalidToken() {
        assertThrows(InvalidTokenException.class,
                () -> jwtUtil.decode(INVALID_TOKEN));
    }

    @DisplayName("decode 메소드는 '', '   ', null 이 주어진다면 '유효하지 않은 토큰입니다.' 라는 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @NullSource
    void decodeWithEmptyToken(String input) {
        assertThrows(InvalidTokenException.class,
                () -> jwtUtil.decode(input));
    }

}
