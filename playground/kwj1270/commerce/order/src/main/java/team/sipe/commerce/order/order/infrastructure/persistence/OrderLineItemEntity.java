package team.sipe.commerce.order.order.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Table(name = "order_line_items")
@Entity
public class OrderLineItemEntity {

    @Column(name = "order_line_item_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineItemId;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @OneToMany(mappedBy = "orderLineItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderOptionGroupEntity> orderOptionGroups;

    @Column(name = "product_id", nullable = false)
    private  Long productId;

    @Column(name = "order_line_name", nullable = false)
    private  String orderLineName;

    @Column(name = "order_line_quantity", nullable = false)
    private  int orderLineQuantity;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OrderLineItemEntity() {
    }

    public OrderLineItemEntity(final Long orderLineItemId,
                               final List<OrderOptionGroupEntity> orderOptionGroups, final Long productId,
                               final String orderLineName, final int orderLineQuantity,
                               final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderLineItemId = orderLineItemId;
        this.orderOptionGroups = orderOptionGroups;
        this.productId = productId;
        this.orderLineName = orderLineName;
        this.orderLineQuantity = orderLineQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderLineItemEntity that = (OrderLineItemEntity) o;
        return Objects.equals(orderLineItemId, that.orderLineItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderLineItemId);
    }

    public void setOrder(final OrderEntity orderEntity) {
        this.order = orderEntity;
    }

    public Long getOrderLineItemId() {
        return orderLineItemId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public List<OrderOptionGroupEntity> getOrderOptionGroups() {
        return orderOptionGroups;
    }

    public Long getProductId() {
        return productId;
    }

    public String getOrderLineName() {
        return orderLineName;
    }

    public int getOrderLineQuantity() {
        return orderLineQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
