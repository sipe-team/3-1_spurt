package com.order.perf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Table("products")
public class Product {

    @Id
    @Column("product_id")
    private Long id;

    @Column("order_id")
    private Long orderId; // Order의 ID를 직접 저장

    @Column("product_name")
    private String productName; // 상품 이름

    @Column("description")
    private String description; // 상품 설명

    @Column("price")
    private int price; // 상품 가격

    @Column("bundle_name")
    private String bundleName;   // 상품 묶음 이름

    @Column("bundle_quantity")
    private int bundleQuantity;  // 묶음 수량

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Product() {
    }

    public Product(
            final Long orderId,
            final String productName, final String description,
            final int price,
            final String bundleName, final int bundleQuantity) {
        this.orderId = orderId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.bundleName = bundleName;
        this.bundleQuantity = bundleQuantity;
    }
}
