package team.sipe.commerce.shop.product.api.dto;

import team.sipe.commerce.shop.product.application.command.ProductOptionGroupCommand;

import java.util.List;

public record ProductOptionGroupRequest(
        String productOptionGroupName,
        List<ProductOptionRequest> productOptionRequests
) {
    public ProductOptionGroupCommand toCommand() {
        return new ProductOptionGroupCommand(
                productOptionGroupName,
                productOptionRequests.stream()
                        .map(ProductOptionRequest::toCommand)
                        .toList()
        );
    }
}
