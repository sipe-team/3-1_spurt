package team.sipe.spurt.ecommerce.order.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class OrderServiceV1Tests {
    @Autowired
    private lateinit var service: OrderService

    @Test
    fun v1() {
        val timeInMillis = measureTimeMillis {
            for (i in 1..3) {
                service.get(1)
            }
        }
        println("v1: $timeInMillis ms")
    }
}