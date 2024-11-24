package team.sipe.commerce.order.order.domain;

public class OrderOption {
    private final Long orderOptionId;
    private final Long productOptionId;
    private final String orderOptionName;
    private final Price price;

    public OrderOption(final Long orderOptionId,
                       final Long productOptionId,
                       final String orderOptionName,
                       final Price price) {
        this.orderOptionId = orderOptionId;
        this.productOptionId = productOptionId;
        this.orderOptionName = orderOptionName;
        this.price = price;
    }

    public Price price() {
        return price;
    }
}