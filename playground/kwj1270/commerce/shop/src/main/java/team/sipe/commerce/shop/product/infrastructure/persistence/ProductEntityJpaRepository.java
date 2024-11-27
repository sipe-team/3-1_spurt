package team.sipe.commerce.shop.product.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityJpaRepository extends JpaRepository<ProductEntity, Long> {
}
