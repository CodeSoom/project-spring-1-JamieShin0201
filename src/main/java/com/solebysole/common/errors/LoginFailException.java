package com.solebysole.common.errors;

/**
 * 로그인 실패 예외.
 */
public class LoginFailException extends RuntimeException {

    public LoginFailException() {
        super("로그인에 실패하였습니다.");
    }

}
