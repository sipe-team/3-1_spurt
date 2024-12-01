package team.sipe.commerce.order.order.domain.product;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderProductOption {

    private final long orderProductOptionId;
    private final String orderProductOptionName;
    private final long productPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderProductOption(final long orderProductOptionId, final String orderProductOptionName, final long productPrice, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.orderProductOptionId = orderProductOptionId;
        this.orderProductOptionName = orderProductOptionName;
        this.productPrice = productPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getOrderProductOptionId() {
        return orderProductOptionId;
    }

    public String getOrderProductOptionName() {
        return orderProductOptionName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderProductOption that = (OrderProductOption) o;
        return Objects.equals(getOrderProductOptionId(), that.getOrderProductOptionId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderProductOptionId());
    }
}
