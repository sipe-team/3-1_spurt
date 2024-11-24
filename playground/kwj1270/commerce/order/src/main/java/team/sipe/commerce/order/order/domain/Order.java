package team.sipe.commerce.order.order.domain;


import java.time.LocalDateTime;
import java.util.Optional;

public class Order {

    private final Optional<Long> id;
    private final Long userId;
    private final Long shopId;
    private final OrderLineItems orderLineItems;
    private final OrderPaymentMethod orderPaymentMethod;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Optional<LocalDateTime> orderedAt;
    private final Price totalPrice;

    public Order(final Long id,
                 final Long userId,
                 final Long shopId,
                 final OrderLineItems orderLineItems,
                 final OrderStatus orderStatus,
                 final OrderPaymentMethod orderPaymentMethod,
                 final LocalDateTime createdAt,
                 final LocalDateTime updatedAt,
                 final LocalDateTime orderedAt,
                 final Price totalPrice
    ) {
        this.id = Optional.ofNullable(id);
        this.userId = userId;
        this.shopId = shopId;
        this.orderLineItems = orderLineItems;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderStatus = orderStatus;
        this.orderPaymentMethod = orderPaymentMethod;
        this.orderedAt = Optional.ofNullable(orderedAt);
        this.totalPrice = Optional.ofNullable(totalPrice).orElseGet(orderLineItems::totalPrice);
    }
}
