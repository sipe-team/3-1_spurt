package com.order.perf.domain;

import com.order.perf.common.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "ORDER_PRODUCTS")
@Entity
public class OrderProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count", nullable = false)
    private int count;

    protected OrderProduct() {
    }

    public OrderProduct(final Order order,
                        final Product product,
                        final int count) {
        this.order = order;
        this.product = product;
        this.count = count;
    }
}
