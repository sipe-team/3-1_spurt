package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderOptionGroup {
    private final Long orderOptionGroupId;
    private final Long productOptionGroupId;
    private final String orderOptionGroupName;
    private final List<OrderOption> orderOptions;

    public OrderOptionGroup(final Long orderOptionGroupId,
                            final Long productOptionGroupId,
                            final String orderOptionGroupName,
                            final List<OrderOption> orderOptions) {
        this.orderOptionGroupId = orderOptionGroupId;
        this.productOptionGroupId = productOptionGroupId;
        this.orderOptionGroupName = orderOptionGroupName;
        this.orderOptions = orderOptions;
    }

    public Price calculatePrice() {
        return orderOptions.stream()
                .map(OrderOption::price)
                .reduce(Price.init(), Price::plus);
    }
}