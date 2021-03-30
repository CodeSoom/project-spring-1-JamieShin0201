package com.solebysole.dto;

import com.solebysole.domain.Category;
import com.solebysole.domain.Image;
import com.solebysole.domain.Keyword;
import com.solebysole.domain.Option;
import com.solebysole.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * 상품 상세 정보.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDetailData {

    private Long id;

    private String name;

    private int originalPrice;

    private int discountedPrice;

    private String description;

    private Category category;

    private Set<Keyword> keywords;

    private List<Image> images;

    private List<Option> options;

    @Builder
    private ProductDetailData(Long id, String name, int originalPrice, int discountedPrice,
                              String description, Category category,
                              Set<Keyword> keywords, List<Image> images, List<Option> options) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.description = description;
        this.category = category;
        this.keywords = keywords;
        this.images = images;
        this.options = options;
    }

    public static ProductDetailData of(Product product) {
        return ProductDetailData.builder()
                .id(product.getId())
                .name(product.getName())
                .originalPrice(product.getOriginalPrice())
                .discountedPrice(product.getDiscountedPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .keywords(product.getKeywords())
                .images(product.getImages())
                .options(product.getOptions())
                .build();
    }

}
