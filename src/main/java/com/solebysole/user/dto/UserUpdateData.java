package com.solebysole.user.dto;

import com.solebysole.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateData {

    @NotBlank
    private String name;

    @Builder
    private UserUpdateData(@NotBlank String name) {
        this.name = name;
    }

    public User toEntity() {
        User user = User.builder()
                .name(name)
                .build();
        return user;
    }

}
