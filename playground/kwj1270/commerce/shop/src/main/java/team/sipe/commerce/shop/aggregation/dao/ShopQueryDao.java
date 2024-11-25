package team.sipe.commerce.shop.aggregation.dao;

import org.jooq.DSLContext;
import org.jooq.generated.tables.ShopsJooqEntity;
import org.jooq.generated.tables.pojos.Shops;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShopQueryDao {

    private final DSLContext dslContext;
    private final ShopsJooqEntity SHOPS = ShopsJooqEntity.SHOPS;

    public ShopQueryDao(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional(readOnly = true)
    public Shops findShopById(final Long shopId) {
        return dslContext.selectFrom(SHOPS)
                .where(SHOPS.ID.eq(shopId))
                .fetchOneInto(Shops.class);
    }
}
