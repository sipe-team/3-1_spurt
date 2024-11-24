package team.sipe.commerce.shop.product.domain;

public class ProductOption {
    private final Long productOptionId;
    private final String productOptionName;
    private final ProductPrice productPrice;

    public ProductOption(final Long productOptionId, final String productOptionName, final ProductPrice productPrice) {
        this.productOptionId = productOptionId;
        this.productOptionName = productOptionName;
        this.productPrice = productPrice;
    }
}
