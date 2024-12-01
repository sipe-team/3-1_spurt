package team.sipe.commerce.order.order.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "order_option_groups")
@Entity
public class OrderOptionGroupEntity {

    @Column(name = "order_option_group_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderOptionGroupId;

    @ManyToOne
    @JoinColumn(name = "order_line_item_id", nullable = false)
    private OrderLineItemEntity orderLineItem;

    @OneToMany(mappedBy = "orderOptionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderOptionEntity> orderOptions;

    @Column(name = "order_option_group_name", nullable = false)
    private String orderOptionGroupName;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public OrderOptionGroupEntity() {
    }


    public OrderOptionGroupEntity(final Long orderOptionGroupId, final String orderOptionGroupName,
                                  final List<OrderOptionEntity> orderOptions,
                                  final LocalDateTime createdAt,
                                  final LocalDateTime updatedAt) {
        this.orderOptionGroupId = orderOptionGroupId;
        this.orderOptionGroupName = orderOptionGroupName;
        this.orderOptions = orderOptions.stream().peek(it -> it.setOrderOptionGroup(this)).toList();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setOrderLineItem(final OrderLineItemEntity orderLineItemEntity) {
        this.orderLineItem = orderLineItemEntity;
    }

    public Long getOrderOptionGroupId() {
        return orderOptionGroupId;
    }

    public List<OrderOptionEntity> getOrderOptions() {
        return orderOptions;
    }

    public String getOrderOptionGroupName() {
        return orderOptionGroupName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
