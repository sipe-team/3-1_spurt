package team.sipe.spurt.ecommerce.order.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import team.sipe.spurt.ecommerce.order.dto.response.OrderDetailsResponse
import team.sipe.spurt.ecommerce.order.service.OrderService

@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @GetMapping("/v1/orders/{id}")
    fun v1(
        @PathVariable id: Long,
    ): ResponseEntity<OrderDetailsResponse> {
        return ResponseEntity.ok(
            OrderDetailsResponse(
                orderService.get(id)
            )
        )
    }
}