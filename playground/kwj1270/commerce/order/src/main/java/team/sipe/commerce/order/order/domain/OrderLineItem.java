package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderLineItem {
    private final long orderLineItemId;
    private final long productId;
    private final String orderLineName;
    private final int orderLineCount;
    private final List<OrderOptionGroup> orderOptionGroups;

    public OrderLineItem(long orderLineItemId, long productId, String orderLineName, int orderLineCount, List<OrderOptionGroup> orderOptionGroups) {
        this.orderLineItemId = orderLineItemId;
        this.productId = productId;
        this.orderLineName = orderLineName;
        this.orderLineCount = orderLineCount;
        this.orderOptionGroups = orderOptionGroups;
    }
}

