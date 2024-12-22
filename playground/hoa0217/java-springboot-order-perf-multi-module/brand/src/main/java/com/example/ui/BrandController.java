package com.example.ui;

import com.example.application.BrandManager;
import com.example.application.dto.BrandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandManager brandManager;

    @GetMapping("/api/v1/brand/{brandId}")
    public BrandResponse find(@PathVariable Long brandId) {
        return brandManager.findBrand(brandId);
    }
}
