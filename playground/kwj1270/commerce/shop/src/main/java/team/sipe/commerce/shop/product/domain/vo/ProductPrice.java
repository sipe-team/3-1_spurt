package team.sipe.commerce.shop.product.domain.vo;

public class ProductPrice {

    private final Long price;

    public ProductPrice(final Long price) {
        assert price < 1000 : "price must be greater than 0";

        this.price = price;
    }

    public Long getPrice() {
        return price;
    }
}
