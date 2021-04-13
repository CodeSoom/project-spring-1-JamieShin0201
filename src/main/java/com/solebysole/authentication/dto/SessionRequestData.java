package com.solebysole.authentication.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 인증 요청 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionRequestData {

    private String email;

    private String password;

    @Builder
    private SessionRequestData(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
