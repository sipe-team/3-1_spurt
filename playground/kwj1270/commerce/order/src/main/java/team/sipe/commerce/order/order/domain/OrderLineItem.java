package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderLineItem {

    private final Long orderLineItemId;
    private final Long productId;
    private final String orderLineName;
    private final int orderLineQuantity;
    private final List<OrderOptionGroup> orderOptionGroups;

    public OrderLineItem(Long orderLineItemId, Long productId, String orderLineName, int orderLineQuantity, List<OrderOptionGroup> orderOptionGroups) {
        this.orderLineItemId = orderLineItemId;
        this.productId = productId;
        this.orderLineName = orderLineName;
        this.orderLineQuantity = orderLineQuantity;
        this.orderOptionGroups = orderOptionGroups;
    }

    public Price calculatePrice() {
        return orderOptionGroups.stream()
                .map(OrderOptionGroup::calculatePrice)
                .reduce(Price.init(), Price::plus);
    }
}
