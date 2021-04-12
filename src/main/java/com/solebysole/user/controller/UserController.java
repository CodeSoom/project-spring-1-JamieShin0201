package com.solebysole.user.controller;

import com.solebysole.user.application.UserService;
import com.solebysole.user.dto.UserRegisterData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * 회원과 관련된 HTTP 요청 처리를 담당합니다.
 */
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 주어진 회원 정보로 회원을 생성합니다.
     *
     * @param userRegisterData 회원 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid UserRegisterData userRegisterData) {
        Long savedId = userService.registerUser(userRegisterData);
        return ResponseEntity.created(URI.create("/api/users/" + savedId)).build();
    }

}
