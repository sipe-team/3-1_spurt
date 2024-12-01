package team.sipe.commerce.order.order.domain.product;

import java.time.LocalDateTime;
import java.util.List;

public class OrderProduct {

    private final long orderProductId;
    private final long orderProductShopId;
    private final String orderProductName;
    private final String orderProductDescription;
    private final List<OrderProductOptionGroup> orderProductOptionGroups;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderProduct(final long orderProductId,
                   final Long orderProductShopId,
                   final String orderProductName,
                   final String orderProductDescription,
                   final List<OrderProductOptionGroup> orderProductOptionGroups,
                   final LocalDateTime createdAt,
                   final LocalDateTime updatedAt) {
        this.orderProductId = orderProductId;
        this.orderProductShopId = orderProductShopId;
        this.orderProductName = orderProductName;
        this.orderProductDescription = orderProductDescription;
        this.orderProductOptionGroups = orderProductOptionGroups;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getOrderProductId() {
        return orderProductId;
    }

    public long getOrderProductShopId() {
        return orderProductShopId;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public String getOrderProductDescription() {
        return orderProductDescription;
    }

    public List<OrderProductOptionGroup> getProductOptionGroups() {
        return orderProductOptionGroups;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
