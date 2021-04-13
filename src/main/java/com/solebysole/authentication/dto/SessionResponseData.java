package com.solebysole.authentication.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * 인증 응답 정보.
 */
@Getter
public class SessionResponseData {

    private String accessToken;

    @Builder
    private SessionResponseData(String accessToken) {
        this.accessToken = accessToken;
    }
}
