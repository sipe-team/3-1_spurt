package com.order.perf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Table("orders")
public class Order {

    @Id
    @Column("order_id")
    private Long id;

    @Column("delivery_id")
    private Long deliveryId;

    @Column("refund_id")
    private Long refundId;

    @Column("payment_id")
    private Long paymentId;

    @Column("order_number")
    private int orderNumber; // 주문 번호

    @Column("order_status")
    private String orderStatus; // 주문 상태 (Enum의 이름을 문자열로 저장)

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Order() {
    }

    public Order(final Long deliveryId,
                 final Long refundId,
                 final Long paymentId,
                 int orderNumber, OrderStatus orderStatus) {
        this.deliveryId = deliveryId;
        this.refundId = refundId;
        this.paymentId = paymentId;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus.name(); // Enum의 이름을 문자열로 저장
    }
}
