package me.lovethefeel.jpahistory.product.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Entity
@EntityListeners(ProductListeners.class)
public class Product {

    public static final String SYSTEM = "SYSTEM";
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private Timestamp created;

    private String createBy;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", created=" + created +
                ", createBy='" + createBy + '\'' +
                ", updated=" + updated +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
