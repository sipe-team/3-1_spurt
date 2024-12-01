package com.order.perf.domain.join;

import com.order.perf.common.BaseEntity;
import com.order.perf.domain.Delivery;
import com.order.perf.domain.OrderStatus;
import com.order.perf.domain.Payment;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Table(name = "ORDERS")
@Entity
@AllArgsConstructor
public class OrderJoin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private int orderNumber; // 주문 번호

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<Product> products;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne
    @JoinColumn(name = "refund_id")
    private Refund refund;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    protected OrderJoin() {
    }
}
