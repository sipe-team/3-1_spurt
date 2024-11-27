package team.sipe.commerce.shop.shop.application.command;

public record ShopRegisterCommand(
        Long sellerId,
        String shopName,
        String shopDescription,
        String shopAddress
) {
}
