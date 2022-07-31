package me.lovethefeel.jpahistory.product.dto;

import lombok.Getter;
import me.lovethefeel.jpahistory.product.domain.Product;

@Getter
public class ProductResponse {

    private Long productId;
    private String productName;

    protected ProductResponse() {}

    private ProductResponse(final Long productId, final String productName) {

        this.productId = productId;
        this.productName = productName;
    }

    public static ProductResponse fromResponse(final Product product) {

        return new ProductResponse(product.getId(), product.getProductName());
    }
}
