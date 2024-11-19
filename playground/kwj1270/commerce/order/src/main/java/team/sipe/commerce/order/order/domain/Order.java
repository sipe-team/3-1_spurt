package team.sipe.commerce.order.order.domain;


import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private final long id;
    private final long userId;
    private final long shopId;
    private final OrderLineItems orderLineItems;
    private final LocalDateTime orderedTime;
    private final OrderStatus orderStatus;

    public Order(long id, long userId, long shopId, List<OrderLineItem> orderLineItems, LocalDateTime orderedTime, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
    }
}
