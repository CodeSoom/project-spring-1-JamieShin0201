package com.solebysole.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.OBJECT;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class OrderDocumentation {

    public static RestDocumentationResultHandler createOrder() {
        return document("create-order",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                requestFields(
                        fieldWithPath("orderProductDataList").type(ARRAY).description("주문 상품 정보 목록"),
                        fieldWithPath("orderProductDataList.[].productId").type(NUMBER).description("상품 ID"),
                        fieldWithPath("orderProductDataList.[].count").type(NUMBER).description("상품 개수"),
                        fieldWithPath("address").type(OBJECT).description("주소"),
                        fieldWithPath("address.base").type(STRING).description("기본 주소"),
                        fieldWithPath("address.detail").type(STRING).description("상세 주소"),
                        fieldWithPath("address.zipcode").type(STRING).description("우편번호")
                ),
                responseHeaders(
                        headerWithName("Location").description("생성된 주문 ID")
                ));
    }

}
