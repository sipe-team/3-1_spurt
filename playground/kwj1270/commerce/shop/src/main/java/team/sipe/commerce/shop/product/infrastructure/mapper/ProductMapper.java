package team.sipe.commerce.shop.product.infrastructure.mapper;

import team.sipe.commerce.shop.product.domain.Product;
import team.sipe.commerce.shop.product.domain.ProductOption;
import team.sipe.commerce.shop.product.domain.ProductOptionGroup;
import team.sipe.commerce.shop.product.domain.vo.ProductPrice;
import team.sipe.commerce.shop.product.infrastructure.persistence.ProductEntity;
import team.sipe.commerce.shop.product.infrastructure.persistence.ProductOptionEntity;
import team.sipe.commerce.shop.product.infrastructure.persistence.ProductOptionGroupEntity;

import java.util.List;

public class ProductMapper {
    private ProductMapper() {
    }

    public static ProductEntity toEntity(final Product product) {
        return new ProductEntity(
                product.getId(),
                product.getShopId(),
                product.getProductName(),
                product.getProductDescription(),
                toProductOptionGroupEntity(product.getProductOptionGroups()),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    private static List<ProductOptionGroupEntity> toProductOptionGroupEntity(final List<ProductOptionGroup> productOptionGroups) {
        return productOptionGroups.stream()
                .map(it -> new ProductOptionGroupEntity(
                        it.getProductOptionGroupId(),
                        it.getProductOptionGroupName(),
                        toProductOptionEntity(it.getProductOptions()),
                        it.getCreatedAt(),
                        it.getUpdatedAt()
                ))
                .toList();
    }

    private static List<ProductOptionEntity> toProductOptionEntity(final List<ProductOption> productOptions) {
        return productOptions.stream()
                .map(it -> new ProductOptionEntity(
                        it.getProductOptionId(),
                        it.getProductOptionName(),
                        it.getProductPrice().getPrice(),
                        it.getCreatedAt(),
                        it.getUpdatedAt()
                ))
                .toList();
    }

    public static Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getShopId(),
                productEntity.getName(),
                productEntity.getDescription(),
                toProductOptionGroup(productEntity.getProductOptionGroup()),
                productEntity.getCreatedAt(),
                productEntity.getUpdatedAt()
        );
    }

    private static List<ProductOptionGroup> toProductOptionGroup(final List<ProductOptionGroupEntity> productOptionGroup) {
        return productOptionGroup.stream()
                .map(it -> new ProductOptionGroup(
                        it.getId(),
                        it.getName(),
                        toProductOption(it.getProductOptions()),
                        it.getCreatedAt(),
                        it.getUpdatedAt()
                ))
                .toList();
    }

    private static List<ProductOption> toProductOption(final List<ProductOptionEntity> productOptions) {
        return productOptions.stream()
                .map(it -> new ProductOption(
                        it.getId(),
                        it.getName(),
                        new ProductPrice(it.getPrice()),
                        it.getCreatedAt(),
                        it.getUpdatedAt()
                ))
                .toList();
    }
}
