package team.sipe.spurt.ecommerce.order.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.system.measureTimeMillis

@ActiveProfiles("v2")
@SpringBootTest
class OrderServiceV2Tests {
    @Autowired
    private lateinit var service: OrderService

    @Test
    fun v2() {
        val timeInMillis = measureTimeMillis {
            for (i in 1..3) {
                service.get(1)
            }
        }
        println("v2: $timeInMillis ms")
    }
}