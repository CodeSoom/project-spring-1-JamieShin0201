package com.solebysole.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class CartDocumentation {

    private int count;

    public static RestDocumentationResultHandler getCartProducts() {
        return document("get-cart-products",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                responseFields(
                        fieldWithPath("[].id").type(NUMBER).description("장바구니 상품 ID"),
                        fieldWithPath("[].productId").type(NUMBER).description("상품 ID"),
                        fieldWithPath("[].name").type(STRING).description("상품 이름"),
                        fieldWithPath("[].originalPrice").type(NUMBER).description("상품 원가"),
                        fieldWithPath("[].discountedPrice").type(NUMBER).description("상품 할인가"),
                        fieldWithPath("[].imageUrl").type(STRING).description("상품 이미지 URL"),
                        fieldWithPath("[].count").type(NUMBER).description("상품 개수")
                ));
    }

    public static RestDocumentationResultHandler createCartProduct() {
        return document("create-cart-product",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                requestFields(
                        fieldWithPath("productId").type(NUMBER).description("상품 ID"),
                        fieldWithPath("count").type(NUMBER).description("상품 개수")
                ));
    }

    public static RestDocumentationResultHandler updateCartProduct() {
        return document("update-cart-product",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                pathParameters(
                        parameterWithName("id").description("수정할 장바구니 상품 ID")
                ),
                requestFields(
                        fieldWithPath("count").type(NUMBER).description("개수")
                ));
    }

    public static RestDocumentationResultHandler deleteCartProduct() {
        return document("delete-cart-product",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                pathParameters(
                        parameterWithName("id").description("삭제할 장바구니 상품 ID")
                ));
    }

}
