package com.order.perf.dto;

import com.order.perf.domain.Product;
import lombok.Builder;
import lombok.Getter;

// 상품 상세 정보 DTO
@Getter
public class ProductDetailsResponse {

    private Long productId;
    private String name;
    private String description;
    private int price;

    public ProductDetailsResponse() {}

    @Builder
    public ProductDetailsResponse(final Long productId, final String name, final String description, final int price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ProductDetailsResponse from(final Product product) {
        return ProductDetailsResponse.builder()
                .productId(product.getId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
