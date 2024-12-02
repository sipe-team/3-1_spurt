package team.sipe.spurt.ecommerce.order.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Table(name = "deliveries")
@Entity
class Delivery(
    @Comment("받으실 분")
    @Column(name = "recipient_name")
    val recipientName: String,

    @Comment("연락처")
    @Column(name = "mobile")
    val mobile: String,

    @Comment("배송 주소")
    @Column(name = "address")
    val address: String,

    @Comment("우편 번호")
    @Column(name = "zip_code")
    val zipCode: String,

    @Comment("가게 비밀 번호")
    @Column(name = "store_password")
    val storePassword: String,

    @Comment("배송 메모")
    @Column(name = "delivery_memo")
    val deliveryMemo: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
): team.sipe.spurt.ecommerce.order.common.BaseEntity() {
}

