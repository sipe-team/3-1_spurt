package team.sipe.spurt.ecommerce.order.dto

import java.time.LocalDateTime

data class OrderItemDto(
    val orderId: Long,
    val orderNumber: Long,
    val orderStatus: team.sipe.spurt.ecommerce.order.domain.OrderStatus,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun of(order: team.sipe.spurt.ecommerce.order.domain.Order): team.sipe.spurt.ecommerce.order.dto.OrderItemDto {
            return team.sipe.spurt.ecommerce.order.dto.OrderItemDto(
                orderId = order.id!!,
                orderNumber = order.orderNumber,
                orderStatus = order.orderStatus,
                createdAt = order.createdAt,
                updatedAt = order.updatedAt,
            )
        }
    }
}