package team.sipe.commerce.order.order.infrastructure;

import org.springframework.stereotype.Component;
import team.sipe.commerce.order.order.application.client.OrderProductClient;
import team.sipe.commerce.order.order.domain.product.OrderProduct;
import team.sipe.commerce.order.order.infrastructure.http.ProductRestClient;
import team.sipe.common.dto.ProductHttpResponse;
import team.sipe.commerce.order.order.infrastructure.http.mapper.OrderProductHttpMapper;

@Component
public class OrderProductClientAdapter implements OrderProductClient {

    private final ProductRestClient productRestClient;

    public OrderProductClientAdapter(final ProductRestClient productRestClient) {
        this.productRestClient = productRestClient;
    }

    @Override
    public OrderProduct findByProductId(final long shopId, final long productId) {
        final ProductHttpResponse productHttpResponse = productRestClient.findByProductId(shopId, productId);
        return OrderProductHttpMapper.toDomain(productHttpResponse);
    }
}
