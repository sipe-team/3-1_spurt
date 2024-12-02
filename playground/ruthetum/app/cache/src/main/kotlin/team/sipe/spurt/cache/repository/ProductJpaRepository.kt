package team.sipe.spurt.cache.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.sipe.spurt.cache.domain.ProductEntity

interface ProductJpaRepository : JpaRepository<ProductEntity, Long>