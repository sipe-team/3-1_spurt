package team.sipe.commerce.order.order.application;

import team.sipe.commerce.order.order.domain.product.OrderProduct;

public interface OrderProductClient {
    OrderProduct findByProductId(final long shopId, final long productId);
}
