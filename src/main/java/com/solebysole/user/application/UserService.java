package com.solebysole.user.application;

import com.solebysole.common.errors.UserEmailDuplicationException;
import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import com.solebysole.user.dto.UserRegisterData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원과 관련된 비즈니스 로직을 담당합니다.
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     * 주어진 회원 정보로 회원을 생성하고, 회원의 식별자를 리턴합니다.
     *
     * @param userRegisterData 회원 정보
     * @return 회원의 식별자
     * @throws UserEmailDuplicationException 회원 이메일이 중복될 경우
     */
    public Long registerUser(UserRegisterData userRegisterData) {
        String email = userRegisterData.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new UserEmailDuplicationException(email);
        }

        User user = userRegisterData.toEntity();
        user.changePassword(userRegisterData.getPassword(), passwordEncoder);

        userRepository.save(user);

        return user.getId();
    }

}
