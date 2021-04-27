package com.solebysole.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class ProductDocumentation {

    public static RestDocumentationResultHandler getProducts() {
        return document("get-products",
                responseFields(
                        fieldWithPath("[].id").type(NUMBER).description("상품 ID"),
                        fieldWithPath("[].name").type(STRING).description("상품 이름"),
                        fieldWithPath("[].originalPrice").type(NUMBER).description("상품 원가"),
                        fieldWithPath("[].discountedPrice").type(NUMBER).description("상품 할인가"),
                        fieldWithPath("[].imageUrl").type(STRING).description("상품 이미지 url")
                ));
    }

    public static RestDocumentationResultHandler getProduct() {
        return document("get-product",
                pathParameters(
                        parameterWithName("id").description("조회할 상품 ID")
                ),
                responseFields(
                        fieldWithPath("id").type(NUMBER).description("상품 ID"),
                        fieldWithPath("name").type(STRING).description("상품 이름"),
                        fieldWithPath("originalPrice").type(NUMBER).description("상품 원가"),
                        fieldWithPath("discountedPrice").type(NUMBER).description("상품 할인가"),
                        fieldWithPath("description").type(STRING).description("상품 설명"),
                        fieldWithPath("category").type(STRING).description("상품 카테고리"),
                        fieldWithPath("keywords").type(ARRAY).description("키워드 목록"),
                        fieldWithPath("keywords.[].id").type(NUMBER).description("키워드 ID"),
                        fieldWithPath("keywords.[].name").type(STRING).description("키워드 이름"),
                        fieldWithPath("images").type(ARRAY).description("이미지 목록"),
                        fieldWithPath("images.[].id").type(NUMBER).description("이미지 ID"),
                        fieldWithPath("images.[].url").type(STRING).description("이미지 URL"),
                        fieldWithPath("options").type(ARRAY).description("옵션 목록"),
                        fieldWithPath("options.[].id").type(NUMBER).description("옵션 ID"),
                        fieldWithPath("options.[].name").type(STRING).description("옵션 이름"),
                        fieldWithPath("options.[].additionalPrice").type(NUMBER).description("옵션 추가 요금"),
                        fieldWithPath("options.[].children").type(ARRAY).description("하위 옵션 목록"),
                        fieldWithPath("options.[].children.[].id").type(NUMBER).description("하위 옵션 ID"),
                        fieldWithPath("options.[].children.[].name").type(STRING).description("하위 옵션 명"),
                        fieldWithPath("options.[].children.[].additionalPrice").type(NUMBER).description("하위 옵션 추가 요금"),
                        fieldWithPath("options.[].children.[].children").type(ARRAY).description("하위 옵션 목록")
                ));
    }

    public static RestDocumentationResultHandler createProduct() {
        return document("create-product",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                requestFields(
                        fieldWithPath("name").type(STRING).description("상품 이름"),
                        fieldWithPath("originalPrice").type(NUMBER).description("상품 원가"),
                        fieldWithPath("discountedPrice").type(NUMBER).description("상품 할인가"),
                        fieldWithPath("description").type(STRING).description("상품 설명"),
                        fieldWithPath("category").type(STRING).description("상품 카테고리"),
                        fieldWithPath("keywords").type(ARRAY).description("키워드 목록"),
                        fieldWithPath("keywords.[].id").type(NUMBER).description("키워드 ID"),
                        fieldWithPath("keywords.[].name").type(STRING).description("키워드 이름"),
                        fieldWithPath("images").type(ARRAY).description("이미지 목록"),
                        fieldWithPath("images.[].id").type(NUMBER).description("이미지 ID"),
                        fieldWithPath("images.[].url").type(STRING).description("이미지 URL"),
                        fieldWithPath("options").type(ARRAY).description("옵션 목록"),
                        fieldWithPath("options.[].id").type(NUMBER).description("옵션 ID"),
                        fieldWithPath("options.[].name").type(STRING).description("옵션 이름"),
                        fieldWithPath("options.[].additionalPrice").type(NUMBER).description("옵션 추가 요금"),
                        fieldWithPath("options.[].children").type(ARRAY).description("하위 옵션 목록"),
                        fieldWithPath("options.[].children.[].id").type(NUMBER).description("하위 옵션 ID"),
                        fieldWithPath("options.[].children.[].name").type(STRING).description("하위 옵션 명"),
                        fieldWithPath("options.[].children.[].additionalPrice").type(NUMBER).description("하위 옵션 추가 요금"),
                        fieldWithPath("options.[].children.[].children").type(ARRAY).description("하위 옵션 목록")
                ),
                responseHeaders(
                        headerWithName("Location").description("생성된 상품 ID")
                ));
    }

}
