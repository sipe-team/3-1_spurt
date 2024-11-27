package team.sipe.commerce.shop.shop.domain;

import java.time.LocalDateTime;

public class Shop {
    private final Long shopId;
    private final Long sellerId;
    private final String shopName;
    private final String shopDescription;
    private final String shopAddress;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Shop(final Long shopId, final Long sellerId,
                final String shopName, final String shopDescription,
                final String shopAddress, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.shopId = shopId;
        this.sellerId = sellerId;
        this.shopName = shopName;
        this.shopDescription = shopDescription;
        this.shopAddress = shopAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getShopId() {
        return shopId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
