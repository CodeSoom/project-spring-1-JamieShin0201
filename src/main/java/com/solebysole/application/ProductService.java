package com.solebysole.application;

import com.solebysole.domain.Product;
import com.solebysole.domain.ProductRepository;
import com.solebysole.dto.ProductCreateData;
import com.solebysole.errors.ProductNameDuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 상품과 관련된 비즈니스 로직을 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 주어진 상품 정보로 상품을 생성하고, 상품의 식별자를 리턴합니다.
     *
     * @param productCreateData 상품 정보
     * @return 상품 식별자
     * @throws ProductNameDuplicationException 상품의 이름이 중복될 경우
     */
    public Long createProduct(ProductCreateData productCreateData)
            throws ProductNameDuplicationException {
        String name = productCreateData.getName();
        if (productRepository.existsByName(name)) {
            throw new ProductNameDuplicationException("중복된 상품 이름입니다. 문제의 이름 = " + name);
        }

        Product product = productCreateData.toEntity();
        productRepository.save(product);

        return product.getId();
    }

}
