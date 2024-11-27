package team.sipe.commerce.shop.product.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import team.sipe.common.CommandValidating;

import java.util.List;

public record ProductRegisterCommand(
        @Min(1) Long shopId,
        @NotBlank String productName,
        @NotBlank String productDescription,
        @NotEmpty List<ProductOptionGroupCommand> productOptionGroups
) implements CommandValidating<ProductRegisterCommand> {
    public ProductRegisterCommand(final Long shopId,
                                  final String productName,
                                  final String productDescription,
                                  final List<ProductOptionGroupCommand> productOptionGroups) {
        this.shopId = shopId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productOptionGroups = productOptionGroups;
        validateSelf(this);
    }
}
