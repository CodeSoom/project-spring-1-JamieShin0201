package com.solebysole.common.errors;

/**
 * 장바구니 상품을 찾을 수 없는 예외
 */
public class CartProductNotFoundException extends RuntimeException {

    public CartProductNotFoundException(String message) {
        super(message);
    }

    public CartProductNotFoundException() {
        this("장바구니 상품을 찾을 수 없습니다.");
    }

    public CartProductNotFoundException(Long id) {
        this("주어진 id에 해당하는 장바구니 상품을 찾을 수 없습니다. 문제의 id = " + id);
    }

}
