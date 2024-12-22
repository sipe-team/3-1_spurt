package com.example.exception;

public class ProductIdNotFoundException extends RuntimeException {

    public ProductIdNotFoundException(final Long id) {
        super("상품을 찾을 수 없습니다. productId: " + id);
    }
}
