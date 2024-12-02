package team.sipe.display.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.sipe.display.core.DisplayQueryService

@RequestMapping("/display")
@RestController
class DisplayApi(
    private val displayQueryService: DisplayQueryService
) {

    @GetMapping("/orders/{orderId}")
    fun orders(@PathVariable("orderId") orderId: Long): ResponseEntity<Any> {
        val response = displayQueryService.findById(orderId)
        return ResponseEntity.ok(response)
    }
}
