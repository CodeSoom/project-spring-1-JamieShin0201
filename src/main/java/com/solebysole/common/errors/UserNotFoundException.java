package com.solebysole.common.errors;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        this("회원을 찾을 수 없습니다.");
    }

    public UserNotFoundException(Long id) {
        this("주어진 id에 해당하는 회원을 찾을 수 없습니다. 문제의 id = " + id);
    }

}
