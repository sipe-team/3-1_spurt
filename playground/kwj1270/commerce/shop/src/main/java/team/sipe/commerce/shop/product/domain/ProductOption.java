package team.sipe.commerce.shop.product.domain;

import team.sipe.commerce.shop.product.domain.vo.ProductPrice;

import java.time.LocalDateTime;

public class ProductOption {
    private final Long productOptionId;
    private final String productOptionName;
    private final ProductPrice productPrice;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ProductOption(final Long productOptionId, final String productOptionName, final ProductPrice productPrice, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
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

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
