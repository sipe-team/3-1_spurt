package team.sipe.commerce.shop.shop.infrastructure.mapper;

import team.sipe.commerce.shop.shop.domain.Shop;
import team.sipe.commerce.shop.shop.infrastructure.persistence.ShopEntity;

public class ShopMapper {
    private ShopMapper() {
    }

    public static Shop toDomain(final ShopEntity shopEntity) {
        return new Shop(
                shopEntity.getId(),
                shopEntity.getSellerId(),
                shopEntity.getName(),
                shopEntity.getDescription(),
                shopEntity.getAddress(),
                shopEntity.getCreatedAt(),
                shopEntity.getUpdatedAt()
        );
    }

    public static ShopEntity toEntity(final Shop shop) {
        return new ShopEntity(
                shop.getShopId(),
                shop.getSellerId(),
                shop.getShopName(),
                shop.getShopDescription(),
                shop.getShopAddress(),
                shop.getCreatedAt(),
                shop.getUpdatedAt()
        );
    }
}
