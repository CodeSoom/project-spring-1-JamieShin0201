package com.solebysole.common.errors;

/**
 * 상품을 찾을 수 없는 예외.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException() {
        this("상품을 찾을 수 없습니다.");
    }

    public ProductNotFoundException(Long id) {
        this("주어진 id에 해당하는 상품을 찾을 수 없습니다. 문제의 id = " + id);
    }

}
