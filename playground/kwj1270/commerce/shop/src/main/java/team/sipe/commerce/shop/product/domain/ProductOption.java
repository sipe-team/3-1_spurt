package team.sipe.commerce.shop.product.domain;

public class ProductOption {
    private final Long productOptionId;
    private final String productOptionName;
    private final Price price;

    public ProductOption(final Long productOptionId, final String productOptionName, final Price price) {
        this.productOptionId = productOptionId;
        this.productOptionName = productOptionName;
        this.price = price;
    }
}
