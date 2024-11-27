package team.sipe.commerce.shop.aggregation.api.dto;

public record ProductInfoHttpResponse(
        Long productId,
        String name,
        String description
) {
}