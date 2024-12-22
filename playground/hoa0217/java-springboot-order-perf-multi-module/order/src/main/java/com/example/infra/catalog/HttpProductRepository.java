package com.example.infra.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpProductRepository implements ProductRepository {

    private final RestTemplate restTemplate;

    @Override
    public Product findProduct(final Long productId) {
        return restTemplate.getForObject("http://127.0.0.1:8080/product/{productId}", Product.class,
                productId);
    }
}
