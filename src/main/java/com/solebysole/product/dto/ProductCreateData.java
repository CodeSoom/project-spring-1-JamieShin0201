package com.solebysole.product.dto;

import com.solebysole.product.domain.Category;
import com.solebysole.product.domain.Image;
import com.solebysole.product.domain.Keyword;
import com.solebysole.product.domain.Option;
import com.solebysole.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * 상품 생성 정보.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ProductCreateData {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private int originalPrice;

    private int discountedPrice;

    @NotBlank
    private String description;

    private Category category;

    private Set<Keyword> keywords;

    private List<Image> images;

    private List<Option> options;

    @Builder
    private ProductCreateData(String name, int originalPrice,
                              int discountedPrice, String description, Category category,
                              Set<Keyword> keywords, List<Image> images, List<Option> options) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.description = description;
        this.category = category;
        this.keywords = keywords;
        this.images = images;
        this.options = options;
    }

    public Product toEntity() {
        Product product = Product.builder()
                .name(name)
                .originalPrice(originalPrice)
                .discountedPrice(discountedPrice)
                .description(description)
                .category(category)
                .build();

        keywords.forEach((keyword) -> product.addKeyword(keyword));
        images.forEach((image) -> product.addImage(image));
        options.forEach((option) -> {
            setParentOption(option.getChildren(), option);
            product.addOption(option);
        });

        return product;
    }

    private void setParentOption(List<Option> children, Option parent) {
        children.stream()
                .forEach(option -> option.setParent(parent));
    }

}

