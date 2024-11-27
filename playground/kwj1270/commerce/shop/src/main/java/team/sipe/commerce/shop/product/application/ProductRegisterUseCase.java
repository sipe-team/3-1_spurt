package team.sipe.commerce.shop.product.application;

import team.sipe.commerce.shop.product.application.command.ProductRegisterCommand;

public interface ProductRegisterUseCase {
    Long register(final ProductRegisterCommand command);
}
