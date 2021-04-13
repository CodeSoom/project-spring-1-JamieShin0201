package com.solebysole.user.domain;

import java.util.Optional;

/**
 * 회원 저장소.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
