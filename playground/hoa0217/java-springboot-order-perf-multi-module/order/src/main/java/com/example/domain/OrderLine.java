package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_LINES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Embedded
    OrderProduct product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    public OrderLine(final OrderProduct product, final int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(final Order order) {
        this.order = order;
    }
}
