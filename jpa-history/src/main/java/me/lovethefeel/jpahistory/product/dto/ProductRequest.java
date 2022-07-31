package me.lovethefeel.jpahistory.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.lovethefeel.jpahistory.product.domain.Product;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@JsonInclude(NON_NULL)
public class ProductRequest {

    private Long productId;
    private String productName;

    protected ProductRequest() {}

    private ProductRequest(final String productName) {
        this.productName = productName;
    }

    private ProductRequest(final Long productId, final String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public static ProductRequest from(final String productName) {
        return new ProductRequest(productName);
    }

    public static ProductRequest of(final Long productId, final String productName) {
        return new ProductRequest(productId, productName);
    }

    public Product toCreateEntity() {
        return Product.fromCreate(this.productName);
    }
}
