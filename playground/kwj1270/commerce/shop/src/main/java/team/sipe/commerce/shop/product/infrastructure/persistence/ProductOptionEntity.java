package team.sipe.commerce.shop.product.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "product_options")
@Entity
public class ProductOptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_option_group_id", nullable = false)
    private ProductOptionGroupEntity productOptionGroup;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Long price;

    public ProductOptionEntity() {
    }

    public ProductOptionEntity(final Long id, final String name, final Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setProductOptionGroup(final ProductOptionGroupEntity productOptionGroupEntity) {
        this.productOptionGroup = productOptionGroupEntity;
    }

    public Long getId() {
        return id;
    }

    public ProductOptionGroupEntity getProductOptionGroup() {
        return productOptionGroup;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductOptionEntity that = (ProductOptionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
