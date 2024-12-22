package com.example.infrastructure;

import com.example.domain.Brand;
import com.example.domain.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpBrandRepository implements BrandRepository {

    private final RestTemplate restTemplate;

    @Override
    public Brand findById(final Long id) {
        return restTemplate.getForObject("http://127.0.0.1:8080/api/v1/brand/{brandId}", Brand.class, id);
    }
}
