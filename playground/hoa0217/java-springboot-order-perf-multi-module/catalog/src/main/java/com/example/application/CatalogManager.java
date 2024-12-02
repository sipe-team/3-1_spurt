package com.example.application;

import com.example.domain.Product;
import com.example.domain.ProductRepository;
import com.example.exception.ProductIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogManager {

    private final ProductRepository productRepository;

    public Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException(id));
    }
}
