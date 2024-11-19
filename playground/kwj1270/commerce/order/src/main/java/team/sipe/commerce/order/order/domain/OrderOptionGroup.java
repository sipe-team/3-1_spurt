package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderOptionGroup {
    private final Long orderOptionGroupId;
    private final String orderOptionGroupName;
    private final List<OrderOption> orderOptions;

    public OrderOptionGroup(final Long orderOptionGroupId,
                            final String orderOptionGroupName,
                            final List<OrderOption> orderOptions) {
        this.orderOptionGroupId = orderOptionGroupId;
        this.orderOptionGroupName = orderOptionGroupName;
        this.orderOptions = orderOptions;
    }
}