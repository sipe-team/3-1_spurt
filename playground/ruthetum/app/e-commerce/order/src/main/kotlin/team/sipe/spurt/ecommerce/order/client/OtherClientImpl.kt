package team.sipe.spurt.ecommerce.order.client

//@Component
//class OtherClientImpl(
//    private val restClient: RestClient = RestClient.create()
//) : OtherClient {
//
//    override fun getProductDetails(id: Long): OtherResponse {
//        return call(id)
//    }
//
//    override fun getProductBundleDetailDto(id: Long): OtherResponse {
//        return call(id)
//    }
//
//    override fun getSomethingElse(id: Long): OtherResponse {
//        return call(id)
//    }
//
//    private fun call(id: Long): OtherResponse {
//        return restClient.get()
//            .uri("http://localhost:8081/other/${id}")
//            .retrieve()
//            .body(OtherResponse::class.java)!!
//    }
//}