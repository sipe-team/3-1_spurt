package com.example.infra.catalog;

public record Product(
        Long id,
        String name,
        int price
) {

}
