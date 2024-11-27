package team.sipe.commerce.shop.product.application.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import team.sipe.common.CommandValidating;

import java.util.List;

public record ProductOptionGroupCommand(
        @NotBlank String productOptionGroupName,
        @NotEmpty List<ProductOptionCommand> productOptions
) implements CommandValidating<ProductOptionGroupCommand> {
    public ProductOptionGroupCommand(final String productOptionGroupName,
                                     final List<ProductOptionCommand> productOptions
    ) {
        this.productOptionGroupName = productOptionGroupName;
        this.productOptions = productOptions;
        validateSelf(this);
    }
}
