package team.sipe.commerce.shop.product.application.command;

import java.util.List;

public record ProductRegisterCommand(
        Long shopId,
        String productName,
        String productDescription,
        List<ProductOptionGroupCommand> productOptionGroups
) {
}



