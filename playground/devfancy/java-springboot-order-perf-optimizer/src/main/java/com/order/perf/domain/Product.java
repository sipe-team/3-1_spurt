package com.order.perf.domain;

import com.order.perf.common.BaseTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "PRODUCTS")
@Entity
public class Product extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName; // 상품 이름
    private String description; // 상품 설명
    private int price; // 상품 가격
    private String bundleName;   // 상품 묶음 이름 (예: 크리스마스 선물 세트)
    private int bundleQuantity;  // 해당 묶음에 포함된 상품의 수량
    protected Product() {
    }

    public Product(String productName, String description, int price, String bundleName, int bundleQuantity) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.bundleName = bundleName;
        this.bundleQuantity = bundleQuantity;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getBundleName() {
        return bundleName;
    }

    public int getBundleQuantity() {
        return bundleQuantity;
    }
}
