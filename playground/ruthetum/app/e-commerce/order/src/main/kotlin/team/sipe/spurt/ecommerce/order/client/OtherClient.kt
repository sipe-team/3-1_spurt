package team.sipe.spurt.ecommerce.order.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class OtherClient(
    private val restClient: RestClient = RestClient.create()
) {

    fun getProductDetails(id: Long): OtherResponse {
        return call(id)
    }

    fun getProductBundleDetailDto(id: Long): OtherResponse {
        return call(id)
    }

    fun getSomethingElse(id: Long): OtherResponse {
        return call(id)
    }

    private fun call(id: Long): OtherResponse {
        return restClient.get()
            .uri("http://localhost:8081/other/${id}")
            .retrieve()
            .body(OtherResponse::class.java)!!
    }
}