package team.sipe.commerce.shop.aggregation.dao.dto;

public record ShopProductInformation(
        Long shopId,
        String shopName,
        String shopDescription,
        Long productId,
        String productName,
        String productDescription
) {
}