package team.sipe.commerce.shop.aggregation.api.dto;

import java.time.LocalDateTime;

public record ShopInformationResponse(
        Long shopId,
        Long sellerId,
        String shopName,
        String shopDescription,
        String shopAddress,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
