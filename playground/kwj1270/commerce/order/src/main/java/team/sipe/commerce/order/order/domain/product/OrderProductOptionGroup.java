package team.sipe.commerce.order.order.domain.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderProductOptionGroup {

    private final long orderProductOptionGroupId;
    private final String orderProductOptionGroupName;
    private final List<OrderProductOption> orderProductOptions;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderProductOptionGroup(final long orderProductOptionGroupId, final String orderProductOptionGroupName,
                                   final List<OrderProductOption> orderProductOptions, final LocalDateTime createdAt,
                                   final LocalDateTime updatedAt
    ) {
        this.orderProductOptionGroupId = orderProductOptionGroupId;
        this.orderProductOptionGroupName = orderProductOptionGroupName;
        this.orderProductOptions = orderProductOptions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getOrderProductOptionGroupId() {
        return orderProductOptionGroupId;
    }

    public String getOrderProductOptionGroupName() {
        return orderProductOptionGroupName;
    }

    public List<OrderProductOption> getOrderProductOptions() {
        return orderProductOptions;
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
        final OrderProductOptionGroup that = (OrderProductOptionGroup) o;
        return Objects.equals(getOrderProductOptionGroupId(), that.getOrderProductOptionGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getOrderProductOptionGroupId());
    }
}
