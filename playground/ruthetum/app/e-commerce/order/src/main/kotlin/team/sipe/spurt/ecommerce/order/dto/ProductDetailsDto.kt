package team.sipe.spurt.ecommerce.order.dto

data class ProductDetailsDto(
    val productId: Long,
    val name: String,
    val description: String,
    val price: Int,
)