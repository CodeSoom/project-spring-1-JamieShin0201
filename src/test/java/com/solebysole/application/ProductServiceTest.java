package com.solebysole.application;

import com.solebysole.domain.Category;
import com.solebysole.domain.Image;
import com.solebysole.domain.Keyword;
import com.solebysole.domain.Product;
import com.solebysole.domain.ProductRepository;
import com.solebysole.dto.ProductCreateData;
import com.solebysole.errors.ProductNameDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("ProductService 클래스")
class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository = mock(ProductRepository.class);

    private ProductCreateData productCreateData;
    private ProductCreateData duplicatedProductCreateData;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);

        productCreateData = createProductCreateData("만두 지갑");
        duplicatedProductCreateData = createProductCreateData("만두 지갑");
    }

    @Nested
    @DisplayName("createProduct")
    class Describe_createProduct {
        @Nested
        @DisplayName("올바른 상품 정보가 주어진다면")
        class Context_with_product {
            @BeforeEach
            void setUp() {
                given(productRepository.save(any(Product.class)))
                        .will(invocation -> invocation.<Product>getArgument(0));
            }

            @Test
            @DisplayName("상품을 생성한다.")
            void it_create_product() {
                productService.createProduct(productCreateData);

                verify(productRepository).save(any(Product.class));
            }
        }

        @Nested
        @DisplayName("중복된 상품 이름이 주어진다면")
        class Context_with_duplicated_product_name {
            @BeforeEach
            void setUp() {
                given(productRepository.existsByName(duplicatedProductCreateData.getName()))
                        .willReturn(true);
            }

            @Test
            @DisplayName("'중복된 상품 이름입니다.' 라는 예외가 발생한다.")
            void it_throws_exception() {
                assertThrows(ProductNameDuplicationException.class,
                        () -> productService.createProduct(productCreateData));
            }
        }
    }

    private ProductCreateData createProductCreateData(String name) {
        Set<Keyword> keywords = new HashSet<>(
                Arrays.asList(new Keyword("가죽"), new Keyword("지갑")));

        List<Image> images = Arrays.asList(
                new Image("url1"),
                new Image("url2"));

        ProductCreateData productCreateData = ProductCreateData.builder()
                .name(name)
                .originalPrice(50000)
                .discountedPrice(4000)
                .description("가죽 지갑입니다.")
                .category(Category.WALLET)
                .keywords(keywords)
                .images(images)
                .options(new ArrayList<>())
                .build();

        return productCreateData;
    }

}
