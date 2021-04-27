package com.solebysole.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class AuthenticationDocumentation {

    public static RestDocumentationResultHandler createSession() {
        return document("create-session",
                requestFields(
                        fieldWithPath("email").type(STRING).description("회원 이메일"),
                        fieldWithPath("password").type(STRING).description("회원 비밀번호")
                ),
                responseFields(
                        fieldWithPath("accessToken").description("사용자 인증 수단, 액세스 토큰 값")
                ));
    }

}
