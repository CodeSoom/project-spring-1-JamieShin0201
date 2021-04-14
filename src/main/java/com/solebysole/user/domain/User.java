package com.solebysole.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 회원.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    private Role role;

    private boolean deleted;

    @Builder
    protected User(Long id, String name, String email, String password,
                   AuthProvider provider, String providerId, Role role, boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.role = role;
        this.deleted = deleted;
    }

    /**
     * 주어진 회원으로 현재 회원을 갱신합니다.
     *
     * @param source 갱신할 회원
     */
    public void changeWith(User source) {
        name = source.name;
    }

    /**
     * 주어진 비밀번호로 현재 비밀번호를 변경합니다.
     *
     * @param password 변경할 비밀번호
     * @param passwordEncoder 패스워드 인코더
     */
    public void changePassword(String password,
                               PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    /**
     * 주어진 비밀번호와 현재 비밀번호가 일치하고, 회원이 삭제되지 않았다면 true 를 리턴합니다.
     *
     * @param password 비밀번호
     * @param passwordEncoder 패스워드 인코더
     */
    public boolean authenticate(String password,
                                PasswordEncoder passwordEncoder) {
        return !deleted && passwordEncoder.matches(password, this.password);
    }

    public User getUser() {
        return this;
    }

}
