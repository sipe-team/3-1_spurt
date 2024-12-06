package team.sipe.commerce.order.order.infrastructure.http.mapper;

import team.sipe.commerce.order.order.domain.product.OrderProduct;
import team.sipe.commerce.order.order.domain.product.OrderProductOption;
import team.sipe.commerce.order.order.domain.product.OrderProductOptionGroup;
import team.sipe.common.dto.ProductHttpResponse;
import team.sipe.common.dto.ProductOptionGroupHttpResponse;
import team.sipe.common.dto.ProductOptionHttpResponse;

public class OrderProductHttpMapper {
    private OrderProductHttpMapper() {
    }

    public static OrderProduct toDomain(final ProductHttpResponse productHttpResponse) {
        return new OrderProduct(
                productHttpResponse.productId(),
                productHttpResponse.productShopId(),
                productHttpResponse.productName(),
                productHttpResponse.productDescription(),
                productHttpResponse.productOptionGroups().stream()
                        .map(OrderProductHttpMapper::toDomain)
                        .toList(),
                productHttpResponse.createdAt(),
                productHttpResponse.updatedAt()
        );
    }

    private static OrderProductOptionGroup toDomain(final ProductOptionGroupHttpResponse productOptionGroupHttpResponses) {
        return new OrderProductOptionGroup(
                productOptionGroupHttpResponses.orderProductOptionGroupId(),
                productOptionGroupHttpResponses.orderProductOptionGroupName(),
                productOptionGroupHttpResponses.orderProductOptions().stream()
                        .map(OrderProductHttpMapper::toDomain)
                        .toList(),
                productOptionGroupHttpResponses.createdAt(),
                productOptionGroupHttpResponses.updatedAt()
        );
    }

    private static OrderProductOption toDomain(ProductOptionHttpResponse productOptionHttpResponse) {
        return new OrderProductOption(
                productOptionHttpResponse.productOptionId(),
                productOptionHttpResponse.productOptionName(),
                productOptionHttpResponse.orderProductOptionPrice(),
                productOptionHttpResponse.createdAt(),
                productOptionHttpResponse.updatedAt()
        );
    }
}
