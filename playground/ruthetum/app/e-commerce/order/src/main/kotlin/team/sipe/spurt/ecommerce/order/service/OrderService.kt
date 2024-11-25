package team.sipe.spurt.ecommerce.order.service

import team.sipe.spurt.ecommerce.order.dto.OrderDetailDto

interface OrderService {
    fun get(id: Long): OrderDetailDto
}