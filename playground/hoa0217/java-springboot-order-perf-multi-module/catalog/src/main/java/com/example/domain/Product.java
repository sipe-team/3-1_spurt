package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "product")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long brandId;

    private Long categoryId;

    private String name;

    private int price;

    private String description;

    public Product(final Long brandId, final Long categoryId, final String name, final int price,
            final String description) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
