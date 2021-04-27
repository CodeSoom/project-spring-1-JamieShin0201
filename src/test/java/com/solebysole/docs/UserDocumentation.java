package com.solebysole.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class UserDocumentation {

    public static RestDocumentationResultHandler createUser() {
        return document("create-user",
                requestFields(
                        fieldWithPath("email").type(STRING).description("회원 이메일"),
                        fieldWithPath("name").type(STRING).description("회원 이름"),
                        fieldWithPath("password").type(STRING).description("회원 비밀번호")
                ),
                responseHeaders(
                        headerWithName("Location").description("생성된 회원 식별자")
                ));
    }

    public static RestDocumentationResultHandler getUser() {
        return document("get-user",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                responseFields(
                        fieldWithPath("id").type(NUMBER).description("회원 식별자"),
                        fieldWithPath("email").type(STRING).description("회원 이메일"),
                        fieldWithPath("name").type(STRING).description("회원 이름"),
                        fieldWithPath("password").type(STRING).description("암호화된 회원 비밀번호")
                ));
    }

    public static RestDocumentationResultHandler updateUser() {
        return document("update-user",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ),
                requestFields(
                        fieldWithPath("name").type(STRING).description("회원 이름")
                ));
    }

    public static RestDocumentationResultHandler deleteUser() {
        return document("delete-user",
                requestHeaders(
                        headerWithName("Authorization").description("사용자 인증 수단, 액세스 토큰 값")
                ));
    }

}
