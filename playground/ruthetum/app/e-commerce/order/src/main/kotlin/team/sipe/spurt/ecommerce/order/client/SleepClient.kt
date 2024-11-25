package team.sipe.spurt.ecommerce.order.client

import org.springframework.stereotype.Component
import team.sipe.spurt.ecommerce.order.logger

@Component
class SleepClient: OtherClient {
    private val log = logger()

    override fun getProductDetails(id: Long): OtherResponse {
        log.info("Sleeping for getProductDetails")
        Thread.sleep(500)
        return OtherResponse()
    }

    override fun getProductBundleDetailDto(id: Long): OtherResponse {
        log.info("Sleeping for getProductBundleDetailDto")
        Thread.sleep(500)
        return OtherResponse()
    }

    override fun getSomethingElse(id: Long): OtherResponse {
        log.info("Sleeping for getSomethingElse")
        Thread.sleep(500)
        return OtherResponse()
    }
}