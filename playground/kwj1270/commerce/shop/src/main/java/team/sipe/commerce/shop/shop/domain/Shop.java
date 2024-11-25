package team.sipe.commerce.shop.shop.domain;

public class Shop {
    private final Long shopId;
    private final Long sellerId;
    private final String shopName;
    private final String shopDescription;
    private final String shopAddress;
    private final String shopCsNumber;

    public Shop(final Long shopId,
                final Long sellerId,
                final String shopName,
                final String shopDescription,
                final String shopAddress,
                final String shopCsNumber
    ) {
        this.shopId = shopId;
        this.sellerId = sellerId;
        this.shopName = shopName;
        this.shopDescription = shopDescription;
        this.shopAddress = shopAddress;
        this.shopCsNumber = shopCsNumber;
    }
}
