package team.sipe.commerce.shop.shop.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.shop.shop.application.command.ShopRegisterCommand;
import team.sipe.commerce.shop.shop.domain.Shop;

import java.time.LocalDateTime;

@Service
public class ShopFactory {

    public Shop init(final ShopRegisterCommand shopRegisterCommand) {
        return new Shop(
                null,
                shopRegisterCommand.sellerId(),
                shopRegisterCommand.shopName(),
                shopRegisterCommand.shopDescription(),
                shopRegisterCommand.shopAddress(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
