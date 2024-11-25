package team.sipe.commerce.shop.product.domain;

import java.util.List;

public class Product {
    private final Long id;
    private final Long shopId;
    private final String productName;
    private final String productDescription;
    private final List<ProductOptionGroup> productOptionGroups;

    public Product(final Long id,
                   final Long shopId,
                   final String productName,
                   final String productDescription,
                   final List<ProductOptionGroup> productOptionGroups) {
        this.id = id;
        this.shopId = shopId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productOptionGroups = productOptionGroups;
    }
}
