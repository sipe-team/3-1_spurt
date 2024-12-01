package team.sipe.commerce.order.order.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderOptionEntity {

    @Column(name = "order_option_id")
    @Id @GeneratedValue
    private Long orderOptionId;

    @ManyToOne
    @JoinColumn(name = "order_option_group_id", nullable = false)
    private OrderOptionGroupEntity orderOptionGroup;

    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId;

    @Column(name = "order_option_name", nullable = false)
    private String orderOptionName;

    @Column(name = "order_option_price", nullable = false)
    private long orderOptionPrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OrderOptionEntity() {
    }

    public OrderOptionEntity(final Long orderOptionId, final Long productOptionId, final String orderOptionName,
                             final long price,
                             final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderOptionId = orderOptionId;
        this.productOptionId = productOptionId;
        this.orderOptionName = orderOptionName;
        this.orderOptionPrice = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setOrderOptionGroup(final OrderOptionGroupEntity orderOptionGroupEntity) {
        this.orderOptionGroup = orderOptionGroupEntity;
    }

    public Long getOrderOptionId() {
        return orderOptionId;
    }

    public OrderOptionGroupEntity getOrderOptionGroup() {
        return orderOptionGroup;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public String getOrderOptionName() {
        return orderOptionName;
    }

    public long getOrderOptionPrice() {
        return orderOptionPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
