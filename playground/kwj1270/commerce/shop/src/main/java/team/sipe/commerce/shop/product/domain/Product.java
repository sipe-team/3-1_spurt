package team.sipe.commerce.shop.product.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Product {

    private final Long id;
    private final Long shopId;
    private final String productName;
    private final String productDescription;
    private final List<ProductOptionGroup> productOptionGroups;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Product(final Long id,
                   final Long shopId,
                   final String productName,
                   final String productDescription,
                   final List<ProductOptionGroup> productOptionGroups,
                   final LocalDateTime createdAt,
                   final LocalDateTime updatedAt) {
        this.id = id;
        this.shopId = shopId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productOptionGroups = productOptionGroups;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public List<ProductOptionGroup> getProductOptionGroups() {
        return productOptionGroups;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
