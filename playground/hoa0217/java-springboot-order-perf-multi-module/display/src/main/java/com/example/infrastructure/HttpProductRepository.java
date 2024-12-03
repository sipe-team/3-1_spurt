package com.example.infrastructure;

import com.example.domain.Product;
import com.example.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpProductRepository implements ProductRepository {

    private final RestTemplate restTemplate;

    @Override
    public List<Product> findAll() {
        final ProductsResponse products = restTemplate.getForObject(
                "http://127.0.0.1:8082/api/v1/product", ProductsResponse.class);
        return products.products();
    }
}
