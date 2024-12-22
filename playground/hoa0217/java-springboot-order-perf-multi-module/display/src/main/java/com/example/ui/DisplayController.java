package com.example.ui;

import com.example.application.DisplayManager;
import com.example.application.dto.ProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayManager displayManager;

    @GetMapping("/api/v1/display")
    public ResponseEntity<ProductsResponse> display() {
        return ResponseEntity.ok(displayManager.findAllProduct());
    }

    @GetMapping("/api/v1/display/cache/local")
    public ResponseEntity<ProductsResponse> displayLocalCache() {
        return ResponseEntity.ok(displayManager.findAllProductLocalCache());
    }

    @GetMapping("/api/v1/display/cache/remote")
    public ResponseEntity<ProductsResponse> displayRemoteCache() {
        return ResponseEntity.ok(displayManager.findAllProductRemoteCache());
    }

    @GetMapping("/api/v1/display/cache/hybrid")
    public ResponseEntity<ProductsResponse> displayHybridCache() {
        return ResponseEntity.ok(displayManager.findAllProductHybridCache());
    }
}
