package me.lovethefeel.jpahistory.product.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class ProductHistory {

    @Id
    @Column(name = "product_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "comment")
    private String comment;

    private Timestamp created;

    private String createBy;

    protected ProductHistory() {}

    private ProductHistory(final Long productId, final String productName, final String comment) {
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.productId = productId;
        this.productName = productName;
        this.comment = comment;
        this.created = timestamp;
        this.createBy = productName;
    }

    public static ProductHistory from(final Long productId, final String productName, final String comment) {
        return new ProductHistory(productId, productName, comment);
    }

    public static ProductHistory fromEntity(final Product product, final String comment) {
        return new ProductHistory(product.getId(), product.getProductName(), comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductHistory that = (ProductHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductHistory{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
