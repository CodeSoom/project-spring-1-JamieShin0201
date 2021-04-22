package com.solebysole.cart.controller;

import com.solebysole.authentication.CurrentUser;
import com.solebysole.cart.application.CartProductService;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 장바구니 상품과 관련된 HTTP 요청 처리를 담당합니다.
 */
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin
@RequestMapping("/api/cart")
@RestController
public class CartProductController {

    private final CartProductService cartProductService;

    /**
     * 주어진 장바구니 상품 정보로 장바구니 상품을 생성합니다.
     *
     * @param user 현재 회원
     * @param cartProductCreateData 장바구니 상품 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> create(
            @CurrentUser User user,
            @RequestBody @Valid CartProductCreateData cartProductCreateData
    ) {
        cartProductService.crateCartProduct(user, cartProductCreateData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
