package com.order.perf.dto;

import com.order.perf.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

// 주문 항목 DTO
@Getter
public class OrderItemDto {

    private final Long orderId;
    private int orderNumber;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderItemDto(final Long orderId, final int orderNumber,
                        final OrderStatus orderStatus,
                        final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
