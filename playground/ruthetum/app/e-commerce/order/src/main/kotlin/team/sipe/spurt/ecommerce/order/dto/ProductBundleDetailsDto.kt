package team.sipe.spurt.ecommerce.order.dto

data class ProductBundleDetailsDto(
    val bundleName: String,
    val bundleQuantity: Int,
) {
    companion object {
        fun of(
            bundleName: String,
            bundleQuantity: Int,
        ) = team.sipe.spurt.ecommerce.order.dto.ProductBundleDetailsDto(
            bundleName = bundleName,
            bundleQuantity = bundleQuantity,
        )
    }
}