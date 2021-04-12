package com.solebysole.user.domain;

/**
 * 회원 저장소.
 */
public interface UserRepository {

    User save(User user);

    boolean existsByEmail(String email);

}
