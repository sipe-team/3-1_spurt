package team.sipe.commerce.shop.product.api.dto;

import team.sipe.commerce.shop.product.application.command.ProductOptionCommand;

public record ProductOptionRequest(
        String productOptionName,
        Long productOptionPrice
) {

    public ProductOptionCommand toCommand() {
        return new ProductOptionCommand(productOptionName, productOptionPrice);
    }
}
