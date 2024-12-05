package team.sipe.spurt.cache.repository.impl

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component
import team.sipe.spurt.cache.domain.ProductEntity
import team.sipe.spurt.cache.repository.ProductCustomRepository
import team.sipe.spurt.cache.repository.ProductJpaRepository

@Component
class ProductCustomRepositoryImpl(
    @PersistenceContext
    private val entityManager: EntityManager,
    private val jpaRepository: ProductJpaRepository,
) : ProductCustomRepository {

    override fun findByIds(ids: Set<Long>): List<ProductEntity> {
        if (ids.isEmpty()) {
            return emptyList()
        }

        val cache = entityManager.entityManagerFactory.cache
        val entities = mutableListOf<ProductEntity>()
        val nonCachingIds = mutableSetOf<Long>()

        for (id in ids) {
            if (cache.contains(ProductEntity::class.java, id)) {
                jpaRepository.findById(id).ifPresent { entities.add(it) }
            } else {
                nonCachingIds.add(id)
            }
        }

        if (nonCachingIds.isNotEmpty()) {
            entities.addAll(jpaRepository.findAllById(nonCachingIds))
        }

        return entities
    }
}