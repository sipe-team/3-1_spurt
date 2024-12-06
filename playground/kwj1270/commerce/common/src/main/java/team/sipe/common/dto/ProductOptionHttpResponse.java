package team.sipe.common.dto;

import java.time.LocalDateTime;

public record ProductOptionHttpResponse(
        Long productOptionId,
        String productOptionName,
        long orderProductOptionPrice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}