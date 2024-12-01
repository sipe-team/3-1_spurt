package com.order.perf.domain;

import com.order.perf.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Table(name = "PRODUCTS")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_name", nullable = false)
    private String productName; // 상품 이름

    @Column(name = "description", nullable = false)
    private String description; // 상품 설명

    @Column(name = "price", nullable = false)
    private int price; // 상품 가격

    @Column(name = "bundle_name", nullable = false)
    private String bundleName;   // 상품 묶음 이름 (예: 크리스마스 선물 세트)

    @Column(name = "bundle_quantity", nullable = false)
    private int bundleQuantity;  // 해당 묶음에 포함된 상품의 수량
    protected Product() {
    }

    public Product(
            final Order order,
            final String productName, final String description,
            final int price,
            final String bundleName, final int bundleQuantity) {
        this.order = order;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.bundleName = bundleName;
        this.bundleQuantity = bundleQuantity;
    }
}
