package com.order.perf.dto;

import lombok.Getter;

// 상품 묶음 관련 정보 DTO
@Getter
public class ProductBundleDetailsDto {

    private String bundleName;
    private int bundleQuantity;
}
