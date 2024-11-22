package com.order.perf.controller;

import com.order.perf.dto.response.OrderDetailsResponse;
import com.order.perf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/async/{orderId}")
    public OrderDetailsResponse getOrderDetails(@PathVariable Long orderId) {

        return orderService.getOrderDetailsResponse(orderId);
    }

    @GetMapping(path = "/join/{orderId}")
    public OrderDetailsResponse getOrderDetailsJoin(@PathVariable Long orderId) {

        return orderService.getOrderByJoinDetailsResponse(orderId);
    }

}