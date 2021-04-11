package com.solebysole.user.dto;

import com.solebysole.user.domain.AuthProvider;
import com.solebysole.user.domain.Role;
import com.solebysole.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * 회원 가입 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRegisterData {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @Builder
    private UserRegisterData(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User toEntity() {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .provider(AuthProvider.LOCAL)
                .role(Role.ROLE_USER)
                .build();

        return user;
    }

}
