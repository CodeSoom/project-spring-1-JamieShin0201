package com.solebysole.user.infra;

import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회원 저장소.
 */
public interface JpaUserRepository
        extends UserRepository, JpaRepository<User, Long> {

    User save(User user);

    boolean existsByEmail(String email);

}
