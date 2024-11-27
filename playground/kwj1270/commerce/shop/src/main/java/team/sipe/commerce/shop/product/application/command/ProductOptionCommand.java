package team.sipe.commerce.shop.product.application.command;

public record ProductOptionCommand(
        String productOptionName,
        Long productOptionPrice
) {
}