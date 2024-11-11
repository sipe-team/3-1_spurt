package com.poc.performance;

public record Country(
        Long id,
        String name,
        String code,
        String dialCode
) {
}