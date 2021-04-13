package com.solebysole.common.errors;

/**
 * 유효하지 않은 토큰 예외.
 */
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.");
    }

    public InvalidTokenException(String token) {
        super("유효하지 않은 토큰입니다. 문제의 토큰 = " + token);
    }

}
