package team.sipe.commerce.shop.product.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Table(name = "products")
@Entity
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOptionGroupEntity> productOptionGroup;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProductEntity() {
    }

    public ProductEntity(final Long id, final Long shopId,
                         final String name, final String description,
                         final List<ProductOptionGroupEntity> productOptionGroup,
                         final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.description = description;
        this.productOptionGroup = productOptionGroup.stream().peek(it -> it.setProduct(this)).toList();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ProductOptionGroupEntity> getProductOptionGroup() {
        return productOptionGroup;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
