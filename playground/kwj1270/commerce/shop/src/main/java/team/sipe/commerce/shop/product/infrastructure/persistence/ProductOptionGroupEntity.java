package team.sipe.commerce.shop.product.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Table(name = "product_option_groups")
@Entity
public class ProductOptionGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "productOptionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOptionEntity> productOptions;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ProductOptionGroupEntity() {
    }

    public ProductOptionGroupEntity(final Long id, final String name, final List<ProductOptionEntity> productOptions, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.productOptions = productOptions.stream().peek(it -> it.setProductOptionGroup(this)).toList();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setProduct(final ProductEntity productEntity) {
        this.product = productEntity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public List<ProductOptionEntity> getProductOptions() {
        return productOptions;
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
        final ProductOptionGroupEntity that = (ProductOptionGroupEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
