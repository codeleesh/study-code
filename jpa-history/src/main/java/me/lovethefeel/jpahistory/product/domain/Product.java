package me.lovethefeel.jpahistory.product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static lombok.AccessLevel.*;

@Getter
@Entity
@ToString
@AllArgsConstructor(access = PRIVATE)
@EntityListeners({ProductListeners.class, AuditingEntityListener.class})
public class Product {

    public static final String SYSTEM = "SYSTEM";
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    @LastModifiedDate
    private Timestamp updated;

    private String updateBy;

    protected Product() {}

    private Product(final String productName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.productName = productName;
        this.created = timestamp;
        this.createBy = SYSTEM;
        this.updated = timestamp;
        this.updateBy = SYSTEM;
    }

    public static Product fromCreate(final String productName) {
        return new Product(productName);
    }

    public static Product of(final Long id, final String productName, final Timestamp createBy, final String created
            , final Timestamp updated, final String updateBy) {

        return new Product(id, productName, createBy, created, updated, updateBy);
    }

    public void updateName(final String changeProductName) {

        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.productName = changeProductName;
        this.updated = timestamp;
        this.updateBy = SYSTEM;
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
}
