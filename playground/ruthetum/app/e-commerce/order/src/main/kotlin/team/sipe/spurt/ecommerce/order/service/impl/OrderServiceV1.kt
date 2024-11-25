package team.sipe.spurt.ecommerce.order.service.impl

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import team.sipe.spurt.ecommerce.order.client.OtherClient
import team.sipe.spurt.ecommerce.order.dto.OrderDetailDto
import team.sipe.spurt.ecommerce.order.service.OrderService

@Profile("v1")
@Service
class OrderServiceV1(
    private val client: OtherClient,
) : OrderService {

    /**
     * 도메인이 모두 각자의 서비스에서 관리되는 경우 (모두 Blocking 요청)
     */
    override fun get(id: Long): OrderDetailDto {
        client.getProductDetails(id)
        client.getProductBundleDetailDto(id)
        client.getSomethingElse(id)
        return OrderDetailDto(id)
    }
}