package com.example.ui;

import com.example.application.OrderManager;
import com.example.application.dto.OrderSheet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderManager orderManager;

    @PostMapping("/api/v1/order")
    public ResponseEntity<Void> order(@RequestBody OrderSheet orderSheet) {
        orderManager.order(orderSheet);
        return ResponseEntity.ok().build();
    }
}
