package com.example.application;

import com.example.application.dto.ProductResponse;
import com.example.domain.Brand;
import com.example.domain.BrandRepository;
import com.example.domain.Category;
import com.example.domain.CategoryRepository;
import com.example.domain.Product;
import com.example.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisplayManager {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductResponse> findAllProduct() {
        final List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> {
                    final Brand brand = brandRepository.findById(product.categoryId());
                    final Category category = categoryRepository.findById(product.categoryId());
                    return new ProductResponse(
                            product.id(),
                            brand.id(),
                            brand.name(),
                            category.id(),
                            category.name(),
                            product.name(),
                            product.price(),
                            product.description()
                    );
                }).toList();
    }
}
