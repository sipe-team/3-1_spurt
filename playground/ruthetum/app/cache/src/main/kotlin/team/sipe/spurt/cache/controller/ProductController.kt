package team.sipe.spurt.cache.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.sipe.spurt.cache.repository.ProductCustomRepository
import team.sipe.spurt.cache.repository.ProductJpaRepository

@RestController
class ProductController(
    private val jpaRepository: ProductJpaRepository,
    private val customRepository: ProductCustomRepository,
) {
    private fun getRandomProductIds(): Set<Long> {
        return (5000L until 5050L).shuffled().take(5).toSet()
    }

    /**
     * hibernate second level cache
     */
    @GetMapping("/products/n")
    fun getProducts(
        @RequestParam("cache") cache: Boolean = false
    ): String {
        val ids = getRandomProductIds()
        if (cache) {
            customRepository.findByIds(ids)
        } else {
            jpaRepository.findAllById(ids)
        }
        return ids.joinToString { it.toString() }
    }


}

