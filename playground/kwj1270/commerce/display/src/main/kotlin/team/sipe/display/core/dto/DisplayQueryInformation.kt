package team.sipe.display.core.dto

data class DisplayQueryInformation(
    val orderId: Long,
    val orderStatus: String,
    val deliveryId: Long,
    val deliveryStatus: String,
)