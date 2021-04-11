package com.solebysole.product.dto;

import com.solebysole.product.domain.Image;
import com.solebysole.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상품 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductData {

    private Long id;

    private String name;

    private int originalPrice;

    private int discountedPrice;

    private String imageUrl;

    @Builder
    private ProductData(Long id, String name, int originalPrice,
                        int discountedPrice, String imageUrl) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.imageUrl = imageUrl;
    }

    public static ProductData of(Product product) {
        Image mainImage = product.getImages().get(0);

        return ProductData.builder()
                .id(product.getId())
                .name(product.getName())
                .originalPrice(product.getOriginalPrice())
                .discountedPrice(product.getDiscountedPrice())
                .imageUrl(mainImage.getUrl())
                .build();
    }

}
