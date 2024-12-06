package team.sipe.commerce.order.aggregation.dao;

import org.jooq.DSLContext;
import org.jooq.generated.tables.OrdersJooqEntity;
import org.springframework.stereotype.Component;
import team.sipe.commerce.order.aggregation.dao.dto.OrderSimpleInformation;

@Component
public class OrderQueryDao {

    private final DSLContext dslContext;
    private final OrdersJooqEntity ORDERS = OrdersJooqEntity.ORDERS;

    public OrderQueryDao(final DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public OrderSimpleInformation findOrderById(final Long orderId) {
        return dslContext
                .select(
                        ORDERS.ID.as("orderId"),
                        ORDERS.ORDER_STATUS.as("orderStatus")
                )
                .from(ORDERS)
                .where(ORDERS.ID.eq(orderId))
                .fetchOneInto(OrderSimpleInformation.class);
    }
}
