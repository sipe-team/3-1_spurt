package com.example.exception;

public class CategoryIdNotFoundException extends RuntimeException {

    public CategoryIdNotFoundException(final Long id) {
        super("카테고리를 찾을 수 없습니다. id: " + id);
    }
}
