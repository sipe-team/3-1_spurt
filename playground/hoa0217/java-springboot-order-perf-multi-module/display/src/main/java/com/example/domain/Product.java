package com.example.domain;

public record Product (
        Long id,
        Long brandId,
        Long categoryId,
        String name,
        int price,
        String description
){

}
