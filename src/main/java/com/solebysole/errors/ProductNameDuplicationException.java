package com.solebysole.errors;

/**
 * 상품 이름 중복 예외.
 */
public class ProductNameDuplicationException extends RuntimeException {

    public ProductNameDuplicationException(String message) {
        super(message);
    }

    public ProductNameDuplicationException() {
        this("중복된 상품 이름입니다.");
    }

}
