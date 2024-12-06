package team.sipe.commerce.order.order.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OrderLineItem {

    private final Long orderLineItemId;
    private final Long productId;
    private final String orderLineName;
    private final int orderLineQuantity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<OrderOptionGroup> orderOptionGroups;

    public OrderLineItem(final Long orderLineItemId, final Long productId,
                         final String orderLineName, final int orderLineQuantity,
                         final List<OrderOptionGroup> orderOptionGroups,
                         final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderLineItemId = orderLineItemId;
        this.productId = productId;
        this.orderLineName = orderLineName;
        this.orderLineQuantity = orderLineQuantity;
        this.orderOptionGroups = orderOptionGroups;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Price calculatePrice() {
        return orderOptionGroups.stream()
                .map(OrderOptionGroup::calculatePrice)
                .reduce(Price.init(), Price::plus);
    }

    public Long getOrderLineItemId() {
        return orderLineItemId;
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

    public List<OrderOptionGroup> getOrderOptionGroups() {
        return orderOptionGroups;
    }
}
