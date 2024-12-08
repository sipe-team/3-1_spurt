package com.example.ui;

import com.example.application.CategoryManager;
import com.example.application.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryManager categoryManager;

    @GetMapping("/api/v1/category/{categoryId}")
    public CategoryResponse find(@PathVariable Long categoryId) {
        return categoryManager.findCategory(categoryId);
    }
}
