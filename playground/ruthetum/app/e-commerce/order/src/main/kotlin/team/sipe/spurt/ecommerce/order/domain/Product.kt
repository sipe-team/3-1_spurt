package team.sipe.spurt.ecommerce.order.domain

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Table(name = "products")
@Entity
class Product(
    @Comment("상품 이름")
    @Column(name = "product_name")
    val productName: String,

    @Comment("상품 설명")
    @Column(name = "description")
    val description: String,

    @Comment("상품 가격")
    @Column(name = "price")
    val price: Int,

    @Comment("상품 묶음 이름")
    @Column(name = "bundle_name")
    val bundleName: String,

    @Comment("상품 묶음 수량")
    @Column(name = "bundle_quantity")
    val bundleQuantity: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : team.sipe.spurt.ecommerce.order.common.BaseEntity()