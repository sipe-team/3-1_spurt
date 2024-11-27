package team.sipe.commerce.shop.shop.application;

import team.sipe.commerce.shop.shop.application.command.ShopRegisterCommand;

public interface ShopRegisterUseCase {
    Long register(ShopRegisterCommand shopRegisterCommand);
}
