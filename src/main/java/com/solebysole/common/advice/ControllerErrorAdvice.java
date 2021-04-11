package com.solebysole.common.advice;

import com.solebysole.common.dto.ErrorResponse;
import com.solebysole.common.errors.ProductNameDuplicationException;
import com.solebysole.common.errors.ProductNotFoundException;
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNameDuplication(ProductNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e);
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
