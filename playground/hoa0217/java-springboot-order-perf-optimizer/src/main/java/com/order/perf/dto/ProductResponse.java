package com.order.perf.dto;

public record ProductResponse(
    Long productId,
    String name,
    String description,
    int price,
    String bundleName,
    int bundleQuantity
) {

}
