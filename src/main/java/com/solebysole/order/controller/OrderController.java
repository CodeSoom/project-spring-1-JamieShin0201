package com.solebysole.order.controller;

import com.solebysole.authentication.CurrentUser;
import com.solebysole.order.application.OrderService;
import com.solebysole.order.dto.OrderCreateData;
import com.solebysole.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * 주문과 관련된 HTTP 요청 처리를 담당합니다.
 */
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin
@RequestMapping("/api/order")
@RestController
public class OrderController {

    private final OrderService orderService;

    /**
     * 주어진 주문 정보로 주문합니다.
     *
     * @param orderCreateData 주문 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> create(
            @CurrentUser User user,
            @RequestBody @Valid OrderCreateData orderCreateData) {
        Long savedId = orderService.order(user, orderCreateData);
        return ResponseEntity.created(URI.create("/api/order/" + savedId)).build();
    }

}
