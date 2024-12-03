package com.example.exception;

public class BrandIdNotFoundException extends RuntimeException {

    public BrandIdNotFoundException(final Long id) {
        super("브랜드를 찾을 수 없습니다. id: " + id);
    }
}
