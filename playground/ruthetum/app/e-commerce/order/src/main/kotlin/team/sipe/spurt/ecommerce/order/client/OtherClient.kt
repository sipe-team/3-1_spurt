package team.sipe.spurt.ecommerce.order.client

interface OtherClient {
    fun getProductDetails(id: Long): OtherResponse
    fun getProductBundleDetailDto(id: Long): OtherResponse
    fun getSomethingElse(id: Long): OtherResponse
}