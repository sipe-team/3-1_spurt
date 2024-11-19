package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderOptionGroup {
    private final long orderOptionGroupId;
    private final String orderOptionGroupName;
    private final List<OrderOption> orderOptions;

    public OrderOptionGroup(long orderOptionGroupId, String orderOptionGroupName, List<OrderOption> orderOptions) {
        this.orderOptionGroupId = orderOptionGroupId;
        this.orderOptionGroupName = orderOptionGroupName;
        this.orderOptions = orderOptions;
    }
}