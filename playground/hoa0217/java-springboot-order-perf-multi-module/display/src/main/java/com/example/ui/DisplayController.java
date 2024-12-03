package com.example.ui;

import com.example.application.DisplayManager;
import com.example.application.dto.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayManager displayManager;

    @GetMapping("/api/v1/display")
    public ResponseEntity<List<ProductResponse>> display() {
        return ResponseEntity.ok(displayManager.findAllProduct());
    }
}
