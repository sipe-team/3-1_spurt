package team.sipe.commerce.shop.product.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.sipe.commerce.shop.product.domain.Product;
import team.sipe.commerce.shop.product.domain.ProductRepository;
import team.sipe.commerce.shop.product.infrastructure.mapper.ProductMapper;
import team.sipe.commerce.shop.product.infrastructure.persistence.ProductEntity;
import team.sipe.commerce.shop.product.infrastructure.persistence.ProductEntityJpaRepository;

import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductEntityJpaRepository productEntityJpaRepository;

    public ProductRepositoryAdapter(final ProductEntityJpaRepository productEntityJpaRepository) {
        this.productEntityJpaRepository = productEntityJpaRepository;
    }

    @Override
    @Transactional
    public Product register(final Product product) {
        final ProductEntity entity = ProductMapper.toEntity(product);
        return Optional.ofNullable(productEntityJpaRepository.save(entity))
                .map(ProductMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Product not registered"));
    }
}
