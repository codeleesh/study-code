package com.lovethefeel.webflux.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Table(name = "product")
@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "product_name", length = 100)
    private String name;

    @Enumerated(STRING)
    @Column(name = "product_type", length = 50)
    private ProductStatus status;

    @Column(name = "product_count", length = 1000)
    private int count;

    public static Product from(final String name, final ProductStatus status, final int count) {
        return new Product(null, name, status, count);
    }

    public static Product from(final Long id, final String name, final ProductStatus type, final int count) {
        return new Product(id, name, type, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", count=" + count +
                '}';
    }
}
