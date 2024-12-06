package team.sipe.commerce.order.order.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OrderOptionGroup {
    private final Long orderOptionGroupId;
    private final String orderOptionGroupName;
    private final List<OrderOption> orderOptions;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderOptionGroup(final Long orderOptionGroupId,
                            final String orderOptionGroupName,
                            final List<OrderOption> orderOptions,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt) {
        this.orderOptionGroupId = orderOptionGroupId;
        this.orderOptionGroupName = orderOptionGroupName;
        this.orderOptions = orderOptions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Price calculatePrice() {
        return orderOptions.stream()
                .map(OrderOption::getOrderPrice)
                .reduce(Price.init(), Price::plus);
    }

    public Long getOrderOptionGroupId() {
        return orderOptionGroupId;
    }

    public String getOrderOptionGroupName() {
        return orderOptionGroupName;
    }

    public List<OrderOption> getOrderOptions() {
        return orderOptions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}