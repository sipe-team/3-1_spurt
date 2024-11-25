package team.sipe.commerce.shop.shop.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.shop.shop.application.command.ShopRegisterCommand;
import team.sipe.commerce.shop.shop.domain.Shop;
import team.sipe.commerce.shop.shop.domain.ShopRepository;

import java.time.LocalDateTime;

@Service
public class ShopService implements ShopRegisterUseCase {

    private final ShopRepository shopRepository;

    public ShopService(final ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public Long register(final ShopRegisterCommand shopRegisterCommand) {
        final Shop shop = shopRepository.save(init(shopRegisterCommand));
        return shop.getShopId();
    }

    public static Shop init(final ShopRegisterCommand shopRegisterCommand) {
        return new Shop(
                null,
                shopRegisterCommand.sellerId(),
                shopRegisterCommand.shopName(),
                shopRegisterCommand.shopDescription(),
                shopRegisterCommand.shopAddress(),
                LocalDateTime.now(),
                null
        );
    }
}

