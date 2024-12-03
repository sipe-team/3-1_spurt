package com.example.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private UUID orderNumber;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderLine> lines = new ArrayList<>();

    public Order(final Long userId, final List<OrderLine> lines) {
        this.userId = userId;
        this.orderNumber = UUID.randomUUID();
        this.orderStatus = OrderStatus.PENDING;

        if (!lines.isEmpty()) {
            lines.forEach(line -> line.setProduct(this));
            this.lines.addAll(lines);
        }
    }
}
