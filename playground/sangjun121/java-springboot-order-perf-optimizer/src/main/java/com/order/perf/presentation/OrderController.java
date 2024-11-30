package com.order.perf.presentation;

import com.order.perf.application.dto.OrderDetailsResponse;
import com.order.perf.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/api/orders/sync/{id}")
    public ResponseEntity<OrderDetailsResponse> findOrderDetailBySync(@PathVariable final Long id) {
        OrderDetailsResponse response = orderService.findOrderDetailsBySync(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/api/orders/async/{id}")
    public ResponseEntity<OrderDetailsResponse> findOrderDetailByAsync(@PathVariable final Long id) {
        OrderDetailsResponse response = orderService.findOrderDetailsByAsync(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}