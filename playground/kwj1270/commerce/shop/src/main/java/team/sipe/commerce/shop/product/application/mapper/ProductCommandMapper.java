package team.sipe.commerce.shop.product.application.mapper;

import team.sipe.commerce.shop.product.application.command.ProductOptionGroupCommand;
import team.sipe.commerce.shop.product.application.command.ProductRegisterCommand;
import team.sipe.commerce.shop.product.domain.Product;
import team.sipe.commerce.shop.product.domain.ProductOption;
import team.sipe.commerce.shop.product.domain.ProductOptionGroup;
import team.sipe.commerce.shop.product.domain.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;

public class ProductCommandMapper {

    private ProductCommandMapper() {
    }

    public static Product init(final ProductRegisterCommand command) {
        return new Product(
                null,
                command.shopId(),
                command.productName(),
                command.productDescription(),
                initProductOptionGroup(command),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private static List<ProductOptionGroup> initProductOptionGroup(final ProductRegisterCommand command) {
        return command.productOptionGroups()
                .stream()
                .map(it -> new ProductOptionGroup(
                        null,
                        it.productOptionGroupName(),
                        initProductOption(it),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ))
                .toList();
    }

    private static List<ProductOption> initProductOption(final ProductOptionGroupCommand productOptionGroupCommand) {
        return productOptionGroupCommand.productOptions()
                .stream()
                .map(it -> new ProductOption(
                        null,
                        it.productOptionName(),
                        new ProductPrice(it.productOptionPrice())
                ))
                .toList();
    }
}
