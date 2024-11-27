package team.sipe.commerce.shop.product.domain;

import java.time.LocalDateTime;
import java.util.List;

public class ProductOptionGroup {
    private final Long productOptionGroupId;
    private final String productOptionGroupName;
    private final List<ProductOption> productOptions;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductOptionGroup(final Long productOptionGroupId, final String productOptionGroupName,
                              final List<ProductOption> productOptions, final LocalDateTime createdAt,
                              final LocalDateTime updatedAt
    ) {
        this.productOptionGroupId = productOptionGroupId;
        this.productOptionGroupName = productOptionGroupName;
        this.productOptions = productOptions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getProductOptionGroupId() {
        return productOptionGroupId;
    }

    public String getProductOptionGroupName() {
        return productOptionGroupName;
    }

    public List<ProductOption> getProductOptions() {
        return productOptions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
