package com.solebysole.controller;

import com.solebysole.application.ProductService;
import com.solebysole.dto.ProductCreateData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * 상품과 관련된 HTTP 요청 처리를 담당합니다.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 주어진 상품 정보로 상품을 생성합니다.
     *
     * @param productCreateData 상품 정보
     * @return 응답 정보
     */
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ProductCreateData productCreateData) {
        Long savedId = productService.createProduct(productCreateData);
        return ResponseEntity.created(URI.create("/api/products/" + savedId)).build();
    }

}
