package com.solebysole.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 상품 옵션.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Option {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int additionalPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Option parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Option> children = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Option(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public Option(String name, Option... children) {
        this.name = name;
        this.children = Arrays.asList(children);
    }

    public void setParent(Option parent) {
        this.parent = parent;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
