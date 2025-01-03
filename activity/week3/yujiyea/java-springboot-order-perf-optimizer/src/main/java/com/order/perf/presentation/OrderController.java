package com.order.perf.presentation;

import com.order.perf.application.dto.OrderDetailsResponse;
import com.order.perf.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/api/orders/{id}")
    public ResponseEntity<OrderDetailsResponse> findByOrderDetail(@PathVariable final Long id) throws ExecutionException, InterruptedException {
        OrderDetailsResponse response = orderService.findOrderDetails(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}