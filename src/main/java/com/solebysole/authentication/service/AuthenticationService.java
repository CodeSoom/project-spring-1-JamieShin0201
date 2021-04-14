package com.solebysole.authentication.service;


import com.solebysole.authentication.utils.JwtUtil;
import com.solebysole.common.errors.InvalidTokenException;
import com.solebysole.common.errors.LoginFailException;
import com.solebysole.common.errors.UserNotFoundException;
import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 인증과 관련된 비즈니스 로직을 담당합니다.
 */
@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository,
                                 JwtUtil jwtUtil,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 주어진 이메일과 비밀번호로 로그인하고, 생성된 액세스 토큰을 리턴합니다.
     *
     * @param email 이메일
     * @param password 비밀번호
     * @return 생성된 액세스 토큰
     * @throws LoginFailException 로그인에 실패하는 경우
     */
    @Transactional(readOnly = true)
    public String login(String email, String password)
            throws LoginFailException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LoginFailException());

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailException();
        }

        return jwtUtil.encode(user.getId());
    }

    /**
     * 주어진 액세스 토큰을 파싱하고, 파싱된 값을 리턴합니다.
     *
     * @param accessToken 액세스 토큰
     * @return 파싱된 값
     * @throws InvalidTokenException 토큰이 유효하지 않을 경우
     */
    public Long parseToken(String accessToken) throws InvalidTokenException {
        Claims claims = jwtUtil.decode(accessToken);
        return claims.get("userId", Long.class);
    }

    /**
     * 주어진 회원 id에 해당하는 회원을 리턴합니다.
     *
     * @param userId 회원 식별자
     * @return 회원
     */
    @Transactional(readOnly = true)
    public User loadUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

}
