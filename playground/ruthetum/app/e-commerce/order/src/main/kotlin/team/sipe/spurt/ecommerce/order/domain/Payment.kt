package team.sipe.spurt.ecommerce.order.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Table(name = "payments")
@Entity
class Payment(
    @Comment("결제 수단")
    @Column(name = "payment_method")
    val paymentMethod: String,

    @Comment("결제 수단 이름")
    @Column(name = "payment_method_name")
    val paymentMethodName: String,

    @Comment("총 결제 금액")
    @Column(name = "payment_amount")
    val paymentAmount: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : team.sipe.spurt.ecommerce.order.common.BaseEntity()