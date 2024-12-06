package team.sipe.display.http

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import team.sipe.display.core.dto.DisplayOrderHttpResponse

@Component
class OrderRestClient {
    private val restClient: RestClient = RestClient.builder()
        .baseUrl("http://localhost:8081")
        .build()

    fun findById(orderId: Long): DisplayOrderHttpResponse = restClient.get()
        .uri("/query/orders/${orderId}")
        .retrieve()
        .body(DisplayOrderHttpResponse::class.java)
        ?: throw RuntimeException("Order not found")
}