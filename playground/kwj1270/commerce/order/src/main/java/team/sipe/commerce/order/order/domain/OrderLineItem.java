package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderLineItem {

    private final Long orderLineItemId;
    private final Long productId;
    private final String orderLineName;
    private final int orderLineCount;
    private final List<OrderOptionGroup> orderOptionGroups;

    public OrderLineItem(Long orderLineItemId, Long productId, String orderLineName, int orderLineCount, List<OrderOptionGroup> orderOptionGroups) {
        this.orderLineItemId = orderLineItemId;
        this.productId = productId;
        this.orderLineName = orderLineName;
        this.orderLineCount = orderLineCount;
        this.orderOptionGroups = orderOptionGroups;
    }
}
