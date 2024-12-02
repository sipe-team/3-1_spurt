package team.sipe.spurt.ecommerce.order.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Table(name = "orders")
@Entity
class Order(
    @Comment("주문 번호")
    @Column(name = "order_number")
    val orderNumber: Long,

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    val orderStatus: team.sipe.spurt.ecommerce.order.domain.OrderStatus,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): team.sipe.spurt.ecommerce.order.common.BaseEntity()