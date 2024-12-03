package com.example.infra.catalog;

public interface ProductRepository {

    Product findProduct(Long productId);
}
