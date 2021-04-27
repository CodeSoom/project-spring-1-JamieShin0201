package com.solebysole.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 주소.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String base;

    private String detail;

    private String zipcode;

    @Builder
    private Address(String base, String detail, String zipcode) {
        this.base = base;
        this.detail = detail;
        this.zipcode = zipcode;
    }

}
