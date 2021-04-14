package com.solebysole.user.dto;

import com.solebysole.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 응답 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseData {

    private Long id;

    private String name;

    private String email;

    private String password;

    @Builder
    private UserResponseData(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserResponseData of(User user) {
        return UserResponseData.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
