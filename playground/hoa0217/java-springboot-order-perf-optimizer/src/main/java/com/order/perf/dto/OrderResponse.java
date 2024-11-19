package com.order.perf.dto;

import com.order.perf.domain.OrderStatus;
import java.time.LocalDateTime;

public record OrderResponse(
    Long orderId,
    int orderNumber,
    OrderStatus orderStatus,
    LocalDateTime createdAt,
    LocalDateTime updateAt
) {

}
