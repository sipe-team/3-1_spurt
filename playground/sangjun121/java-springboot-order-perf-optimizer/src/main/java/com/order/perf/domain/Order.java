package com.order.perf.domain;

import com.order.perf.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "ORDERS")
@Entity
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    private Long deliveryId;
    private Long refundId;
    private Long paymentId;

    @Column(name = "order_number", nullable = false)
    private int orderNumber; // 주문 번호

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts = new ArrayList<>();

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
        this.orderStatus = orderStatus;
    }
}
