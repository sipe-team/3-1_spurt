package team.sipe.commerce.shop.product.api.dto;

import team.sipe.commerce.shop.product.application.command.ProductRegisterCommand;

import java.util.List;

public record ProductRegisterHttpRequest(
        String productName,
        String productDescription,
        List<ProductOptionGroupRequest> productOptionGroupRequest
) {
    public ProductRegisterCommand toCommand(final Long shopId) {
        return new ProductRegisterCommand(
                shopId,
                productName,
                productDescription,
                productOptionGroupRequest.stream()
                        .map(ProductOptionGroupRequest::toCommand)
                        .toList()
        );
    }
}
