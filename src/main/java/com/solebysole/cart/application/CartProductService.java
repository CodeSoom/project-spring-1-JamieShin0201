package com.solebysole.cart.application;

import com.solebysole.cart.domain.CartProduct;
import com.solebysole.cart.domain.CartProductRepository;
import com.solebysole.cart.dto.CartProductCreateData;
import com.solebysole.cart.dto.CartProductData;
import com.solebysole.cart.dto.CartProductUpdateData;
import com.solebysole.common.errors.CartProductNotFoundException;
import com.solebysole.common.errors.ProductNotFoundException;
import com.solebysole.product.domain.Product;
import com.solebysole.product.domain.ProductRepository;
import com.solebysole.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 장바구니 상품과 관련된 비즈니스 로직을 담당합니다.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CartProductService {

    private final CartProductRepository cartProductRepository;

    private final ProductRepository productRepository;

    /**
     * 모든 장바구니 상품을 리턴합니다.
     */
    public List<CartProductData> getCartProducts(User user) {
        List<CartProduct> cartProducts = cartProductRepository.findAllByUserId(user.getId());

        return Collections.unmodifiableList(
                cartProducts.stream()
                        .map(CartProductData::of)
                        .collect(Collectors.toList())
        );
    }

    /**
     * 주어진 장바구니 상품 정보로 장바구니 상품을 생성하고, 만들어진 식별자를 리턴합니다.
     *
     * @param cartProductCreateData 장바구니 상품 생성 정보
     * @return 장바구니 상품 식별자
     * @throws ProductNotFoundException 상품이 존재하지 않을 경우
     */
    @Transactional
    public Long crateCartProduct(User user, CartProductCreateData cartProductCreateData)
            throws ProductNotFoundException {
        Long productId = cartProductCreateData.getProductId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        CartProduct cartProduct = CartProduct.builder()
                .user(user)
                .product(product)
                .count(cartProductCreateData.getCount())
                .build();

        cartProductRepository.save(cartProduct);

        return cartProduct.getId();
    }

    /**
     * 주어진 id에 해당하는 장바구니 상품을 전달받은 정보로 변경합니다.
     *
     * @param id 장바구니 상품 식별자
     * @param cartProductUpdateData 장바구니 상품 변경 정보
     * @throws CartProductNotFoundException 장바구니 상품이 존재하지 않을 경우
     */
    @Transactional
    public void updateCartProduct(Long id, CartProductUpdateData cartProductUpdateData)
            throws CartProductNotFoundException {
        CartProduct cartProduct = findCartProduct(id);

        cartProduct.changeCount(cartProductUpdateData.getCount());
        cartProductRepository.save(cartProduct);
    }

    /**
     * 주어진 id에 해당하는 장바구니 상품을 삭제합니다.
     *
     * @param id 장바구니 상품 식별자
     * @throws CartProductNotFoundException 장바구니 상품이 존재하지 않을 경우
     */
    @Transactional
    public void deleteCartProduct(Long id) throws CartProductNotFoundException {
        CartProduct cartProduct = findCartProduct(id);

        cartProductRepository.delete(cartProduct);
    }

    private CartProduct findCartProduct(Long id) {
        return cartProductRepository.findById(id)
                .orElseThrow(() -> new CartProductNotFoundException(id));
    }

}
