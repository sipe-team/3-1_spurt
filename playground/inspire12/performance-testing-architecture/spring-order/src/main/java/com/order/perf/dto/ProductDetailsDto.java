package com.order.perf.dto;

import lombok.Getter;

// 상품 상세 정보 DTO
@Getter
public class ProductDetailsDto {

    private Long productId;
    private String name;
    private String description;
    private int price;
}
