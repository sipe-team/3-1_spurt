package com.order.perf.controller;

import com.order.perf.dto.OrderDetailResponse;
import com.order.perf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("api/v1/order/{orderId}")
  public ResponseEntity<OrderDetailResponse> findOrderDetail(@PathVariable Long orderId) {
    return ResponseEntity.ok(orderService.findOrderDetail(orderId));
  }
}