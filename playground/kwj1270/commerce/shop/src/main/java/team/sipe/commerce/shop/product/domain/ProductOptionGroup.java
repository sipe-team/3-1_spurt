package team.sipe.commerce.shop.product.domain;

import java.util.List;

public class ProductOptionGroup {
    private final Long productOptionGroupId;
    private final String productOptionGroupName;
    private final List<ProductOption> productOptions;

    public ProductOptionGroup(final long productOptionGroupId, final String productOptionGroupName, final List<ProductOption> productOptions) {
        this.productOptionGroupId = productOptionGroupId;
        this.productOptionGroupName = productOptionGroupName;
        this.productOptions = productOptions;
    }
}
