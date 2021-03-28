package com.solebysole.controller;

import com.solebysole.dto.ErrorResponse;
import com.solebysole.errors.ProductNameDuplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * HTTP 요청 처리 중 발생한 예외들을 담당합니다.
 */
@RestControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(ProductNameDuplicationException.class)
    public ResponseEntity<ErrorResponse> handleProductNameDuplication(ProductNameDuplicationException e) {
        ErrorResponse errorResponse = new ErrorResponse(e);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
