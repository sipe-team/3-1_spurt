package team.sipe.commerce.shop.shop.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.shop.shop.application.command.ShopRegisterCommand;
import team.sipe.commerce.shop.shop.domain.Shop;
import team.sipe.commerce.shop.shop.domain.ShopRepository;

@Service
public class ShopService implements ShopRegisterUseCase {

    private final ShopRepository shopRepository;
    private final ShopFactory shopFactory;

    public ShopService(final ShopRepository shopRepository, final ShopFactory shopFactory) {
        this.shopRepository = shopRepository;
        this.shopFactory = shopFactory;
    }

    @Override
    public Long register(final ShopRegisterCommand shopRegisterCommand) {
        final Shop shop = shopRepository.save(shopFactory.init(shopRegisterCommand));
        return shop.getShopId();
    }
}

