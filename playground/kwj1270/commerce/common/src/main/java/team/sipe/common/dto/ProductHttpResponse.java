package team.sipe.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductHttpResponse(
        Long productId,
        Long productShopId,
        String productName,
        String productDescription,
        List<ProductOptionGroupHttpResponse> productOptionGroups,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}