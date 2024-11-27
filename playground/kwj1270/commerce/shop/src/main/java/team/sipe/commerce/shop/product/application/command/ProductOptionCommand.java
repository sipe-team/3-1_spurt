package team.sipe.commerce.shop.product.application.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import team.sipe.common.CommandValidating;

public record ProductOptionCommand(
        @NotBlank String productOptionName,
        @Min(1000) Long productOptionPrice
) implements CommandValidating<ProductOptionCommand> {
    public ProductOptionCommand(final String productOptionName, final Long productOptionPrice) {
        this.productOptionName = productOptionName;
        this.productOptionPrice = productOptionPrice;
        validateSelf(this);
    }
}