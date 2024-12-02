package team.sipe.display.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import team.sipe.display.core.dto.DisplayQueryInformation
import team.sipe.display.http.DeliveryRestClient
import team.sipe.display.http.OrderRestClient

@Service
class DisplayQueryService(
    private val orderClient: OrderRestClient,
    private val deliveryRestClient: DeliveryRestClient,
) {

    fun findById(orderId: Long): DisplayQueryInformation = runBlocking(Dispatchers.IO) {
        val orderDeferred = async {
            orderClient.findById(orderId)
        }
        val deliveryDeferred = async {
            deliveryRestClient.findByOrderId(orderId)
        }
        val orderResponse = orderDeferred.await()
        val deliveryResponse = deliveryDeferred.await()
        return@runBlocking DisplayQueryInformation(
            orderId = orderResponse.orderId,
            orderStatus = orderResponse.orderStatus,
            deliveryId = deliveryResponse.deliveryId,
            deliveryStatus = deliveryResponse.deliveryStatus,
        )
    }
}
