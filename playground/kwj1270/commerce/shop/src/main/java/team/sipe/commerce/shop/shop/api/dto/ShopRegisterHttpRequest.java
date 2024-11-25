package team.sipe.commerce.shop.shop.api.dto;

import team.sipe.commerce.shop.shop.application.command.ShopRegisterCommand;

public record ShopRegisterHttpRequest(
        Long sellerId,
        String shopName,
        String shopDescription,
        String shopAddress
) {
    public ShopRegisterCommand toCommand() {
        return new ShopRegisterCommand(sellerId(), shopName(), shopDescription(), shopAddress());
    }
}