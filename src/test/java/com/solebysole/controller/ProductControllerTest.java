package com.solebysole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solebysole.application.ProductService;
import com.solebysole.domain.Category;
import com.solebysole.domain.Image;
import com.solebysole.domain.Keyword;
import com.solebysole.dto.ProductCreateData;
import com.solebysole.dto.ProductData;
import com.solebysole.errors.ProductNameDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("ProductController 클래스")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private final Long savedId = 1L;

    private List<ProductData> productDataList;
    private ProductData productData1;
    private ProductData productData2;

    private ProductCreateData productCreateData;
    private ProductCreateData invalidProductCreateData;
    private ProductCreateData duplicatedProductCreateData;

    @BeforeEach
    void setUp() {
        productData1 = createProductData(1L, "지갑1");
        productData2 = createProductData(2L, "지갑2");

        productCreateData = createProductCreateData("만두 지갑");
        invalidProductCreateData = createProductCreateData("");
        duplicatedProductCreateData = createProductCreateData("만두 지갑");
    }

    @Nested
    @DisplayName("GET 요청은")
    class Describe_GET {
        @Nested
        @DisplayName("저장된 상품이 여러개 있다면")
        class Context_with_products {
            @BeforeEach
            void setUp() {
                productDataList = List.of(productData1, productData2);

                given(productService.getProducts())
                        .willReturn(productDataList);
            }

            @Test
            @DisplayName("모든 상품 목록과 상태코드 200 OK 를 응답한다.")
            void it_responds_all_product_data_list() throws Exception {
                mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(2)))
                        .andExpect(status().isOk());
            }
        }

        @Nested
        @DisplayName("저장된 상품이 없다면")
        class Context_without_products {
            @BeforeEach
            void setUp() {
                productDataList = List.of();

                given(productService.getProducts())
                        .willReturn(productDataList);
            }

            @Test
            @DisplayName("비어있는 상품 목록과 상태코드 200 OK 를 응답한다.")
            void it_responds_empty_product_data_list() throws Exception {
                mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(0)))
                        .andExpect(status().isOk());
            }
        }
    }

    @Nested
    @DisplayName("POST 요청은")
    class Describe_POST {
        @Nested
        @DisplayName("올바른 상품 정보가 주어진다면")
        class Context_with_product_data {
            @BeforeEach
            void setUp() {
                given(productService.createProduct(any(ProductCreateData.class)))
                        .willReturn(savedId);
            }

            @Test
            @DisplayName("상태코드 201 Created 를 응답한다.")
            void it_responds_status_code_201() throws Exception {
                mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productCreateData)))
                        .andExpect(header().string("location", "/api/products/" + savedId))
                        .andExpect(status().isCreated());
            }
        }

        @Nested
        @DisplayName("올바르지 않은 상품 정보가 주어진다면")
        class Context_with_invalid_product_data {
            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProductCreateData)))
                        .andExpect(status().isBadRequest());
            }
        }

        @Nested
        @DisplayName("중복된 상품 이름이 주어진다면")
        class Context_with_duplicated_product_data {
            @BeforeEach
            void setUp() {
                given(productService.createProduct(any(ProductCreateData.class)))
                        .willThrow(new ProductNameDuplicationException());
            }

            @Test
            @DisplayName("상태코드 400 Bad Request 를 응답한다.")
            void it_responds_status_code_400() throws Exception {
                mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(duplicatedProductCreateData)))
                        .andExpect(status().isBadRequest());
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

    private ProductData createProductData(Long id, String name) {
        ProductData productData = ProductData.builder()
                .id(id)
                .name(name)
                .originalPrice(50000)
                .discountedPrice(40000)
                .imageUrl("url")
                .build();

        return productData;
    }

}