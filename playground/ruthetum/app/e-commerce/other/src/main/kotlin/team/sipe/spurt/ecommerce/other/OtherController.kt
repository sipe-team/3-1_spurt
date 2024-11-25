package team.sipe.spurt.ecommerce.other

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class OtherController {

    @GetMapping("/other/{id}")
    fun other(
        @PathVariable id: Long,
    ): OtherResponse {
        Thread.sleep(500)
        return OtherResponse()
    }
}