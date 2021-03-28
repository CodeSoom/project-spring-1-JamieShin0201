package com.solebysole.domain;

/**
 * 상품 카테고리.
 */
public enum Category {

    BAG("가방"),
    WALLET("지갑"),
    CARD_WALLET("카드지갑"),
    POUCH("파우치"),
    BELT("벨트"),
    ETC("기타");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
