package com.example.application.dto;

public record ProductResponse(
        Long id,
        Long brandId,
        Long categoryId,
        String name,
        int price,
        String description) {

}
