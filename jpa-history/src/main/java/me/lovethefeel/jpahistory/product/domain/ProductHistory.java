package me.lovethefeel.jpahistory.product.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;

@Entity
@ToString
@AllArgsConstructor(access = PRIVATE)
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(updatable = false)
    private Timestamp created;

    @Column(updatable = false)
    private String createBy;

    protected ProductHistory() {}

    private ProductHistory(final Long productId, final String productName, final String comment) {

        this(null, productId, productName, comment, null, null);
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
}
