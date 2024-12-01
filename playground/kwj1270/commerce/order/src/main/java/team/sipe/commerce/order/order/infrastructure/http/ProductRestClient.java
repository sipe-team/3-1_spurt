package team.sipe.commerce.order.order.infrastructure.http;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import team.sipe.common.dto.ProductHttpResponse;

@Component
public class ProductRestClient {

    private static final String URL = "http://localhost:8080";

    private final RestClient restClient = RestClient.builder()
            .baseUrl(URL)
            .build();

    public ProductHttpResponse findByProductId(final long shopId, final long productId) {
        return restClient.get()
                .uri("/shops/" + shopId + "/products/" + productId)
                .retrieve()
                .body(ProductHttpResponse.class);
    }
}
