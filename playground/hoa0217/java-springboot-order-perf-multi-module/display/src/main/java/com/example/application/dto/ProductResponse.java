package com.example.application.dto;

public record ProductResponse(
        Long productId,
        Long brandId,
        String brandName,
        Long categoryId,
        String categoryName,
        String productName,
        int price,
        String description

) {

}
