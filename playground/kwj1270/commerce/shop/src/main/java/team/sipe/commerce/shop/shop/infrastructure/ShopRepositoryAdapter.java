package team.sipe.commerce.shop.shop.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.sipe.commerce.shop.shop.domain.Shop;
import team.sipe.commerce.shop.shop.domain.ShopRepository;
import team.sipe.commerce.shop.shop.infrastructure.mapper.ShopMapper;
import team.sipe.commerce.shop.shop.infrastructure.persistence.ShopJpaRepository;

import java.util.Optional;

@Component
public class ShopRepositoryAdapter implements ShopRepository {

    private final ShopJpaRepository shopJpaRepository;

    public ShopRepositoryAdapter(final ShopJpaRepository shopJpaRepository) {
        this.shopJpaRepository = shopJpaRepository;
    }

    @Transactional
    public Shop save(final Shop shop) {
        return Optional.ofNullable(shopJpaRepository.save(ShopMapper.toEntity(shop)))
                .map(ShopMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Failed to save shop"));
    }
}
