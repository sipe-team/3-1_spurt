package team.sipe.common.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductOptionGroupHttpResponse(
        Long orderProductOptionGroupId,
        String orderProductOptionGroupName,
        List<ProductOptionHttpResponse> orderProductOptions,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}