package team.sipe.spurt.ecommerce.order.study

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture.allOf
import java.util.concurrent.CompletableFuture.supplyAsync
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

class CoroutineStudy {
    private val coroutineScope = CoroutineScope(Executors.newFixedThreadPool(2).asCoroutineDispatcher())

    @Test
    fun `스레드풀`() {
        val service = ThreadPoolBasedService()

        val time = measureTimeMillis {
            for (i in 1..10) {
                service.call()
            }
        }

        println("스레드풀: $time ms")
    }

    @Test
    fun `코루틴`() {
        val service = CoroutineBasedService()

        val time = measureTimeMillis {
            runBlocking {
                for (i in 1..10) {
                    service.call()
                }
            }
        }

        println("코루틴: $time ms")
    }
}

class ThreadPoolBasedService {
    private val threadPool = Executors.newFixedThreadPool(2)

    fun call() {
        val v1 = supplyAsync(
            { Thread.sleep(500) },
            threadPool
        )
        val v2 = supplyAsync(
            { Thread.sleep(800) },
            threadPool
        )
        val v3 = supplyAsync(
            { Thread.sleep(1000) },
            threadPool
        )
        allOf(v1, v2, v3).join()
        println("${LocalDateTime.now()} 스레드풀 done")
    }
}

class CoroutineBasedService {
    private val coroutineScope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(2).asCoroutineDispatcher())

    suspend fun call() {
        val v1 = coroutineScope.async { delay(500) }
        val v2 = coroutineScope.async { delay(800) }
        val v3 = coroutineScope.async { delay(1000) }
        joinAll(v1, v2, v3)
        println("${LocalDateTime.now()} 코루틴 done")
    }
}