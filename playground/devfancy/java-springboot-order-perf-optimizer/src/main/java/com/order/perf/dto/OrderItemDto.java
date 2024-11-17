package com.order.perf.dto;

import com.order.perf.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

// 주문 항목 DTO
@Getter
public class OrderItemDto {

    private Long orderId;
    private int orderNumber;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
