package com.solebysole.common.dto;

/**
 * 에러 응답.
 */
public class ErrorResponse {

    private final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return message;
    }

}
