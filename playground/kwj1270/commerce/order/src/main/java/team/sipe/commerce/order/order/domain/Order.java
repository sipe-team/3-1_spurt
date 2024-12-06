package team.sipe.commerce.order.order.domain;


import java.time.LocalDateTime;
import java.util.Objects;

public class Order {

    private final Long id;
    private final Long userId;
    private final Long shopId;
    private final OrderLineItem orderLineItem;
    private final OrderPaymentMethod orderPaymentMethod;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final LocalDateTime orderedAt;

    public Order(final Long id,
                 final Long userId,
                 final Long shopId,
                 final OrderLineItem orderLineItem,
                 final OrderStatus orderStatus,
                 final OrderPaymentMethod orderPaymentMethod,
                 final LocalDateTime createdAt,
                 final LocalDateTime updatedAt,
                 final LocalDateTime orderedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderLineItem = orderLineItem;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderStatus = orderStatus;
        this.orderPaymentMethod = orderPaymentMethod;
        this.orderedAt = orderedAt;
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

    public OrderLineItem getOrderLineItem() {
        return orderLineItem;
    }

    public OrderPaymentMethod getOrderPaymentMethod() {
        return orderPaymentMethod;
    }

    public OrderStatus getOrderStatus() {
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

    public Order finished() {
        return new Order(this.id, this.userId, this.shopId, this.orderLineItem, OrderStatus.PAYED, this.orderPaymentMethod, this.createdAt, LocalDateTime.now(), this.orderedAt);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
