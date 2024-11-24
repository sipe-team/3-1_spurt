package team.sipe.commerce.order.order.domain;

import java.util.List;

public class OrderLineItems {

    private final List<OrderLineItem> orderLineItems;

    public OrderLineItems(final List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

    public Price totalPrice() {
        return orderLineItems.stream()
                .map(it -> it.calculatePrice())
                .reduce(Price.init(), Price::plus);
    }
}
