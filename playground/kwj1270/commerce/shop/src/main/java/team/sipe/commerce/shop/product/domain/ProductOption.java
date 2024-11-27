package team.sipe.commerce.shop.product.domain;

import team.sipe.commerce.shop.product.domain.vo.ProductPrice;

public class ProductOption {
    private final Long productOptionId;
    private final String productOptionName;
    private final ProductPrice productPrice;

    public ProductOption(final Long productOptionId, final String productOptionName, final ProductPrice productPrice) {
        this.productOptionId = productOptionId;
        this.productOptionName = productOptionName;
        this.productPrice = productPrice;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public String getProductOptionName() {
        return productOptionName;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }
}
