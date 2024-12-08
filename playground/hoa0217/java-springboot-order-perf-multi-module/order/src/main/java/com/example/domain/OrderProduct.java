package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private int productPrice;

    public OrderProduct(final Long productId, final String productName, final int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
