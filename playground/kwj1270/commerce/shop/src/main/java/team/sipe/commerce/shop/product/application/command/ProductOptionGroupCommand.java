package team.sipe.commerce.shop.product.application.command;

import java.util.List;

public record ProductOptionGroupCommand(
        String productOptionGroupName,
        List<ProductOptionCommand> productOptions
) {
}