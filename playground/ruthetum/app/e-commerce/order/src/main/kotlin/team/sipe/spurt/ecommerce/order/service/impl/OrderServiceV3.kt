package team.sipe.spurt.ecommerce.order.service.impl

import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import team.sipe.spurt.ecommerce.order.client.OtherClient
import team.sipe.spurt.ecommerce.order.dto.OrderDetailDto
import team.sipe.spurt.ecommerce.order.service.OrderService
import java.util.concurrent.CompletableFuture.allOf
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.Executors.newFixedThreadPool

@Profile("v3")
@Service
class OrderServiceV3(
    private val client: OtherClient,
) : OrderService {

    private val asyncPool = newFixedThreadPool(10)

    @Cacheable(key = "#id", value = ["order"])
    override fun get(id: Long): OrderDetailDto {
        getOrderDetail(id)
        return OrderDetailDto(id)
    }

    fun getOrderDetail(id: Long) {
        val productDetail = supplyAsync(
                { client.getProductDetails(id) },
        asyncPool
        )
        val productBundleDetail = supplyAsync(
            { client.getProductBundleDetailDto(id) },
            asyncPool
        )
        val somethingElse = supplyAsync(
            { client.getSomethingElse(id) },
            asyncPool
        )
        allOf(productDetail, productBundleDetail, somethingElse).join()
    }
}