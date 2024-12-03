package com.example.application;

import com.example.application.dto.CategoryResponse;
import com.example.domain.Category;
import com.example.domain.CategoryRepository;
import com.example.exception.CategoryIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryManager {

    private final CategoryRepository categoryRepository;

    public CategoryResponse findCategory(Long id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryIdNotFoundException(id));
        return new CategoryResponse(category.getId(), category.getName());
    }
}
