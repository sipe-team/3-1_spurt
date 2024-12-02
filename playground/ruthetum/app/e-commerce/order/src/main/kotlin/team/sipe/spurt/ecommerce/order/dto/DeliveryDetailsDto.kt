package team.sipe.spurt.ecommerce.order.dto

data class DeliveryDetailsDto(
    val recipientName: String,
    val mobile: String,
    val address: String,
    val zipCode: String,
    val storePassword: String,
    val deliveryMemo: String,
) {
    companion object {
        fun of(
            recipientName: String,
            mobile: String,
            address: String,
            zipCode: String,
            storePassword: String,
            deliveryMemo: String,
        ) = team.sipe.spurt.ecommerce.order.dto.DeliveryDetailsDto(
            recipientName = recipientName,
            mobile = mobile,
            address = address,
            zipCode = zipCode,
            storePassword = storePassword,
            deliveryMemo = deliveryMemo,
        )
    }
}