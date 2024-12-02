package com.example.ui;

import com.example.application.CatalogManager;
import com.example.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final CatalogManager catalogManager;

    @GetMapping("/api/v1/product/{productId}")
    public Product find(@PathVariable Long productId) {
        return catalogManager.findProduct(productId);
    }
}
