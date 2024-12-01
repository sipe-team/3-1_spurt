package team.sipe.commerce.shop.aggregation.dao.dto;


import java.time.LocalDateTime;
import java.util.List;

public class ProductOptionGroupInformation {

    private final Long productOptionGroupId;
    private final String productOptionGroupName;
    private final List<ProductOptionInformation> productOptions;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductOptionGroupInformation(final Long productOptionGroupId, final String productOptionGroupName,
                              final List<ProductOptionInformation> productOptions, final LocalDateTime createdAt,
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

    public List<ProductOptionInformation> getProductOptions() {
        return productOptions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

