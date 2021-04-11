package com.solebysole.common.errors;

/**
 * 회원 이메일 중복 예외.
 */
public class UserEmailDuplicationException extends RuntimeException {

    public UserEmailDuplicationException() {
        super("중복된 이메일 입니다.");
    }

    public UserEmailDuplicationException(String email) {
        super("중복된 이메일 입니다. 문제의 이메일 = " + email);
    }

}
