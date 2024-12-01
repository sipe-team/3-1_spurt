package team.sipe.commerce.shop.aggregation.dao.dto;

import team.sipe.commerce.shop.product.domain.vo.ProductPrice;

import java.time.LocalDateTime;

public class ProductOptionInformation {

    private final Long productOptionId;
    private final String productOptionName;
    private final long productPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductOptionInformation(final Long productOptionId, final String productOptionName, final long productPrice, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.productOptionId = productOptionId;
        this.productOptionName = productOptionName;
        this.productPrice = productPrice;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getProductOptionId() {
        return productOptionId;
    }

    public String getProductOptionName() {
        return productOptionName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

