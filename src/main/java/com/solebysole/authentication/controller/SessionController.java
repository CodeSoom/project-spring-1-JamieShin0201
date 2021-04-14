package com.solebysole.authentication.controller;

import com.solebysole.authentication.dto.SessionRequestData;
import com.solebysole.authentication.dto.SessionResponseData;
import com.solebysole.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 인증과 관련된 HTTP 요청 처리를 담당합니다.
 */
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/session")
@RestController
public class SessionController {

    private final AuthenticationService authenticationService;

    /**
     * 주어진 회원 로그인 정보로 로그인하고 생성된 액세스 토큰을 응답합니다.
     *
     * @param sessionRequestData 회원 로그인 정보
     * @return 생성된 액세스 토큰
     */
    @PostMapping
    public ResponseEntity<SessionResponseData> login(
            @RequestBody SessionRequestData sessionRequestData
    ) {
        String email = sessionRequestData.getEmail();
        String password = sessionRequestData.getPassword();

        String accessToken = authenticationService.login(email, password);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        SessionResponseData.builder()
                                .accessToken(accessToken)
                                .build()
                );
    }

}
