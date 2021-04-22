package com.solebysole.cart.dto;

import com.solebysole.cart.domain.CartProduct;
import com.solebysole.product.domain.Image;
import com.solebysole.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장바구니 상품 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartProductData {

    private Long id;

    private Long productId;

    private String name;

    private int originalPrice;

    private int discountedPrice;

    private String imageUrl;

    private int count;

    @Builder
    private CartProductData(Long id, Long productId, String name,
                            int originalPrice, int discountedPrice, String imageUrl, int count) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.imageUrl = imageUrl;
        this.count = count;
    }

    public static CartProductData of(CartProduct cartProduct) {
        Product product = cartProduct.getProduct();
        Image mainImage = product.getImages().get(0);

        return CartProductData.builder()
                .id(cartProduct.getId())
                .productId(product.getId())
                .name(product.getName())
                .originalPrice(product.getOriginalPrice())
                .discountedPrice(product.getDiscountedPrice())
                .imageUrl(mainImage.getUrl())
                .count(cartProduct.getCount())
                .build();
    }

}
