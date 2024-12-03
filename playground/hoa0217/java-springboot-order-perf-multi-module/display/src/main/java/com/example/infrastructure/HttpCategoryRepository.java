package com.example.infrastructure;

import com.example.domain.Category;
import com.example.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpCategoryRepository implements CategoryRepository {

    private final RestTemplate restTemplate;

    @Override
    public Category findById(final Long id) {
        return restTemplate.getForObject("http://127.0.0.1:8081/api/v1/category/{categoryId}",
                Category.class, id);
    }
}
