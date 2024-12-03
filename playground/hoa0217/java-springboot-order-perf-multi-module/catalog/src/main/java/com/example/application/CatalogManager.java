package com.example.application;

import com.example.application.dto.ProductResponse;
import com.example.application.dto.ProductsResponse;
import com.example.domain.Product;
import com.example.domain.ProductRepository;
import com.example.exception.ProductIdNotFoundException;
import java.util.List;
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

    public ProductsResponse findAllProduct() {
        final List<Product> products = productRepository.findAll();
        final List<ProductResponse> productResponses = products.stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getBrandId(),
                        product.getCategoryId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDescription()
                ))
                .toList();
        return new ProductsResponse(productResponses);
    }
}
