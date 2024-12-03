package com.example.application;

import com.example.application.dto.BrandResponse;
import com.example.domain.Brand;
import com.example.domain.BrandRepository;
import com.example.exception.BrandIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandManager {

    private final BrandRepository brandRepository;

    public BrandResponse findBrand(Long id) {
        final Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandIdNotFoundException(id));
        return new BrandResponse(brand.getId(), brand.getName());
    }
}
