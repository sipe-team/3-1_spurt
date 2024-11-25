package team.sipe.commerce.order.order.domain;


import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private final Long id;
    private final Long userId;
    private final Long shopId;
    private final OrderLineItems orderLineItems;
    private final LocalDateTime orderedTime;
    private final OrderStatus orderStatus;
    private final OrderPaymentMethod orderPaymentMethod;

    public Order(final Long id,
                 final Long userId,
                 final Long shopId,
                 final List<OrderLineItem> orderLineItems,
                 final LocalDateTime orderedTime,
                 final OrderStatus orderStatus,
                 final OrderPaymentMethod orderPaymentMethod) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.orderedTime = orderedTime;
        this.orderStatus = orderStatus;
        this.orderPaymentMethod = orderPaymentMethod;
    }
}
