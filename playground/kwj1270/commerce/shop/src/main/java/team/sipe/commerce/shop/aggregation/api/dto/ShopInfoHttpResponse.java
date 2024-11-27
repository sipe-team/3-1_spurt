package team.sipe.commerce.shop.aggregation.api.dto;

public record ShopInfoHttpResponse(
        Long shopId,
        String name,
        String description
) {
}