package com.solebysole.authentication.utils;

import com.solebysole.common.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

/**
 * JWT 토큰의 부호화, 복호화를 담당합니다.
 */
@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 주어진 회원 id를 이용해 부호화한 JWT 토큰 문자열을 리턴합니다.
     *
     * @param userId 회원 식별자
     * @return JWT 토큰
     */
    public String encode(Long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .signWith(key)
                .compact();
    }

    /**
     * 주어진 토큰을 복호화하고, 복호화된 정보 조각들을 리턴합니다.
     *
     * @param token 토큰
     * @return 정보 조각들
     * @throws InvalidTokenException 토큰이 유효하지 않을 경우
     */
    public Claims decode(String token) throws InvalidTokenException {
        if (token == null || token.isBlank()) {
            throw new InvalidTokenException(token);
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new InvalidTokenException(token);
        }
    }

}
