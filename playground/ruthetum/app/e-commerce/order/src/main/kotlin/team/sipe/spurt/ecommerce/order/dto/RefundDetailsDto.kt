package team.sipe.spurt.ecommerce.order.dto

data class RefundDetailsDto(
    val refundMethodName: String,
    val refundAmount: Int,
    val refundStatus: team.sipe.spurt.ecommerce.order.domain.RefundStatus,
)