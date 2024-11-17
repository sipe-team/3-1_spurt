package com.order.perf.controller;

import com.order.perf.dto.response.OrderDetailsResponse;
import com.order.perf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/{orderId}")
    public OrderDetailsResponse getOrderDetails(@PathVariable Long orderId) {

        return orderService.getOrderDetailsResponse(orderId);
    }



}