package team.sipe.commerce.order.order.domain;

public class OrderOption {
    private final Long orderOptionId;
    private final String orderOptionName;
    private final Price price;

    public OrderOption(final Long orderOptionId, final String orderOptionName, final Price price) {
        this.orderOptionId = orderOptionId;
        this.orderOptionName = orderOptionName;
        this.price = price;
    }
}