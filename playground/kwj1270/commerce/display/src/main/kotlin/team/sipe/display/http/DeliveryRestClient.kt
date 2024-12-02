package team.sipe.display.http

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import team.sipe.display.core.dto.DisplayDeliveryHttpResponse

@Component
class DeliveryRestClient {
    private val restClient: RestClient = RestClient.builder()
        .baseUrl("http://localhost:8082")
        .build()

    fun findByOrderId(orderId: Long): DisplayDeliveryHttpResponse = restClient.get()
        .uri("/query/delivery/orders/${orderId}")
        .retrieve()
        .body(DisplayDeliveryHttpResponse::class.java)
        ?: throw RuntimeException("Delivery not found")
}
