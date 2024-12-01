package team.sipe.commerce.order.order.domain;

import java.time.LocalDateTime;

public class OrderOption {
    private final Long orderOptionId;
    private final Long productOptionId;
    private final String orderOptionName;
    private final Price price;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderOption(final Long orderOptionId,
                       final Long productOptionId,
                       final String orderOptionName,
                       final Price price,
                       final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderOptionId = orderOptionId;
        this.productOptionId = productOptionId;
        this.orderOptionName = orderOptionName;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getOrderOptionId() {
        return orderOptionId;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public String getOrderOptionName() {
        return orderOptionName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Price getOrderPrice() {
        return price;
    }
}