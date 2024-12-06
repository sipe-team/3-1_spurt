package com.order.perf.dto;

import lombok.Getter;

// 상품 묶음 관련 정보 DTO
@Getter
public class ProductBundleDetailsResponse {

    private String bundleName;
    private int bundleQuantity;

    public ProductBundleDetailsResponse() {}

    public ProductBundleDetailsResponse(final String bundleName, final int bundleQuantity) {
        this.bundleName = bundleName;
        this.bundleQuantity = bundleQuantity;
    }
}
