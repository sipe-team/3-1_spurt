package team.sipe.commerce.order.order.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Table(name = "orders")
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderLineItemEntity orderLineItem;

    @Column(name = "order_payment_method", nullable = false)
    private String orderPaymentMethod;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    public OrderEntity() {
    }

    public OrderEntity(final Long id, final Long userId, final Long shopId,
                       final OrderLineItemEntity orderLineItem,
                       final String orderPaymentMethod, final String orderStatus,
                       final LocalDateTime createdAt, final LocalDateTime updatedAt, final LocalDateTime orderedAt) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        orderLineItem.setOrder(this);
        this.orderLineItem = orderLineItem;
        this.orderPaymentMethod = orderPaymentMethod;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderedAt = orderedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public OrderLineItemEntity getOrderLineItem() {
        return orderLineItem;
    }

    public String getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }
}
