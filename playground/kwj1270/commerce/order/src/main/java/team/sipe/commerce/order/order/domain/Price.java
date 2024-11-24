package team.sipe.commerce.order.order.domain;

public class Price {

    private static final int MINIMUM_PRICE = 0;

    private final long price;

    public Price(final long price) {
        if (price < MINIMUM_PRICE) {
            throw new IllegalArgumentException("Price must be greater than or equal to 0");
        }
        this.price = price;
    }

    public static Price init() {
        return new Price(MINIMUM_PRICE);
    }

    public Price plus(final Price other) {
        return new Price(this.price + other.price);
    }
}
