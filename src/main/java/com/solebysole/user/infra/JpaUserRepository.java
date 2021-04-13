package com.solebysole.user.infra;

import com.solebysole.user.domain.User;
import com.solebysole.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 회원 저장소.
 */
public interface JpaUserRepository
        extends UserRepository, JpaRepository<User, Long> {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
