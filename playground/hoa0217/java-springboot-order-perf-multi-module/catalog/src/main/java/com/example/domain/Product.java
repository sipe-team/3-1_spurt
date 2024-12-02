package com.example.domain;

import com.example.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Table(name = "PRODUCTS")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<ProductItem> items = new HashSet<>();

    public Product(final String name, final Set<ProductItem> items) {
        this.name = name;
        if (!items.isEmpty()) {
            this.items.addAll(items);
        }
    }

    public int calculatePrice() {
        return items.stream()
                .map(ProductItem::price)
                .reduce(0, Integer::sum);
    }
}
