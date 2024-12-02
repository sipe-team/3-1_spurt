package team.sipe.spurt.ecommerce.order.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Table(name = "refunds")
@Entity
class Refund(
    @Comment("환불 수단")
    @Column(name = "refund_method_name")
    val refundMethodName: String,

    @Comment("환불 금액")
    @Column(name = "refund_amount")
    val refundAmount: Int,

    @Comment("환불 상태")
    @Column(name = "refund_status")
    @Enumerated(EnumType.STRING)
    val refundStatus: team.sipe.spurt.ecommerce.order.domain.RefundStatus,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : team.sipe.spurt.ecommerce.order.common.BaseEntity()