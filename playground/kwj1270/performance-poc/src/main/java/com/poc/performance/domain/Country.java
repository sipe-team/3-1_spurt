package com.poc.performance.domain;

public record Country(
        Long id,
        String name,
        String code,
        String dialCode
) {
}