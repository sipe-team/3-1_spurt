package com.example.application;

import com.example.application.dto.ProductResponse;
import com.example.application.dto.ProductsResponse;
import com.example.domain.Brand;
import com.example.domain.BrandRepository;
import com.example.domain.Category;
import com.example.domain.CategoryRepository;
import com.example.domain.Product;
import com.example.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisplayManager {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductsResponse findAllProduct() {
        final List<Product> products = productRepository.findAll();
        log.info("find success: " + products.size());
        final List<ProductResponse> responses = products.stream()
                .map(product -> {
                    final Brand brand = brandRepository.findById(product.brandId());
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
        return new ProductsResponse(responses);
    }

    @Cacheable(value = "displayCache")
    public ProductsResponse findAllProductCache() {
        final List<Product> products = productRepository.findAll();
        log.info("find success: " + products.size());
        final List<ProductResponse> responses = products.stream()
                .map(product -> {
                    final Brand brand = brandRepository.findById(product.brandId());
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
        return new ProductsResponse(responses);
    }
}
