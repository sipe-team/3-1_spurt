package team.sipe.spurt.ecommerce.order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class OrderApplication

fun main(args: Array<String>) {
    runApplication<team.sipe.spurt.ecommerce.order.OrderApplication>(*args)
}