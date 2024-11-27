package team.sipe.commerce.shop.shop.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopJpaRepository extends JpaRepository<ShopEntity, Long> {
}
