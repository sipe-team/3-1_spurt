package team.sipe.spurt.cache.repository

import team.sipe.spurt.cache.domain.ProductEntity


interface ProductCustomRepository {
    fun findByIds(ids: Set<Long>): List<ProductEntity>
}