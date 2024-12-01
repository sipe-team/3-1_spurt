package team.sipe.commerce.shop.aggregation.dao;

import org.jooq.DSLContext;
import org.jooq.generated.tables.ProductOptionGroupsJooqEntity;
import org.jooq.generated.tables.ProductOptionsJooqEntity;
import org.jooq.generated.tables.ProductsJooqEntity;
import org.jooq.generated.tables.ShopsJooqEntity;
import org.jooq.generated.tables.pojos.ProductOptionGroups;
import org.jooq.generated.tables.pojos.ProductOptions;
import org.jooq.generated.tables.pojos.Products;
import org.jooq.generated.tables.pojos.Shops;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.sipe.commerce.shop.aggregation.dao.dto.ProductInformation;
import team.sipe.commerce.shop.aggregation.dao.dto.ProductOptionGroupInformation;
import team.sipe.commerce.shop.aggregation.dao.dto.ProductOptionInformation;
import team.sipe.commerce.shop.aggregation.dao.dto.ShopProductInformation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShopProductQueryDao {

    private final DSLContext dslContext;
    private final ShopsJooqEntity SHOPS = ShopsJooqEntity.SHOPS;
    private final ProductsJooqEntity PRODUCTS = ProductsJooqEntity.PRODUCTS;
    private final ProductOptionGroupsJooqEntity PRODUCT_OPTION_GROUPS = ProductOptionGroupsJooqEntity.PRODUCT_OPTION_GROUPS;
    private final ProductOptionsJooqEntity PRODUCT_OPTIONS = ProductOptionsJooqEntity.PRODUCT_OPTIONS;

    public ShopProductQueryDao(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional(readOnly = true)
    public Shops findShopById(final Long shopId) {
        return dslContext.selectFrom(SHOPS)
                .where(SHOPS.ID.eq(shopId))
                .fetchOneInto(Shops.class);
    }

    @Transactional(readOnly = true)
    public List<ShopProductInformation> findAllProductsByShopId(final Long shopId, final Long offset, Long limit) {
        return dslContext.select(
                        DSL.field(SHOPS.ID).as("shopId"),
                        DSL.field(SHOPS.NAME).as("shopName"),
                        DSL.field(SHOPS.DESCRIPTION).as("shopDescription"),
                        DSL.field(PRODUCTS.ID).as("productId"),
                        DSL.field(PRODUCTS.NAME).as("productName"),
                        DSL.field(PRODUCTS.DESCRIPTION).as("productDescription")
                )
                .from(SHOPS)
                .join(PRODUCTS)
                .on(SHOPS.ID.eq(PRODUCTS.SHOP_ID))
                .where(SHOPS.ID.eq(shopId))
                .offset((limit - 1) * offset)
                .limit(limit)
                .fetchInto(ShopProductInformation.class);
    }

    public ProductInformation findByShopIdAndProductId(final Long shopId, final Long productId) {
        final Products products = dslContext.selectFrom(PRODUCTS)
                .where(PRODUCTS.SHOP_ID.eq(shopId).and(PRODUCTS.ID.eq(productId)))
                .fetchOneInto(Products.class);

        final List<ProductOptionGroups> groups = dslContext.selectFrom(PRODUCT_OPTION_GROUPS)
                .where(PRODUCT_OPTION_GROUPS.PRODUCT_ID.eq(products.getId()))
                .fetchInto(ProductOptionGroups.class)
                .stream()
                .toList();

        final Map<Long, List<ProductOptions>> productOptions = dslContext.selectFrom(PRODUCT_OPTIONS)
                .where(PRODUCT_OPTIONS.PRODUCT_OPTION_GROUP_ID.in(groups.stream().map(ProductOptionGroups::getId).toList()))
                .fetchInto(ProductOptions.class)
                .stream()
                .collect(Collectors.groupingBy(ProductOptions::getProductOptionGroupId));

        return new ProductInformation(
                products.getId(),
                products.getShopId(),
                products.getName(),
                products.getDescription(),
                groups.stream()
                        .map(group -> new ProductOptionGroupInformation(
                                group.getId(),
                                group.getName(),
                                productOptions.get(group.getId()).stream()
                                        .map(option -> new ProductOptionInformation(
                                                option.getId(),
                                                option.getName(),
                                                option.getPrice(),
                                                option.getCreatedAt(),
                                                option.getUpdatedAt()
                                        )).toList(),
                                group.getCreatedAt(),
                                group.getUpdatedAt()
                        ))
                        .toList(),
                products.getCreatedAt(),
                products.getUpdatedAt()
        );
    }
}
