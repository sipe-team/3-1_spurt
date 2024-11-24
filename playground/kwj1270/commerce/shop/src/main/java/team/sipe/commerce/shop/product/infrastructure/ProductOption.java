package team.sipe.commerce.shop.product.infrastructure;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "product_option")
@Entity
public class ProductOption {

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

    public ProductOption() {
    }

    public ProductOption(final Long id, final String name, final Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ProductOption that = (ProductOption) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
