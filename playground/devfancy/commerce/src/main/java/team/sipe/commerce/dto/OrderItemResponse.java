package team.sipe.commerce.dto;

import team.sipe.commerce.domain.Order;
import team.sipe.commerce.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// 주문 항목 DTO
@Getter
public class OrderItemResponse {

    private final Long orderId;
    private final Long orderNumber;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public OrderItemResponse(final Long orderId, final Long orderNumber,
                             final OrderStatus orderStatus,
                             final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static OrderItemResponse from(final Order order) {
        return OrderItemResponse.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
