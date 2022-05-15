package com.lovethefeel.webflux.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class ProductResponse {

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_status")
    private ProductStatus productStatus;

    @JsonProperty("product_count")
    private int productCount;

    public static ProductResponse of(final Product saveProduct) {
        return new ProductResponse(saveProduct.getId(), saveProduct.getName(), saveProduct.getStatus(), saveProduct.getCount());
    }

    public static ProductResponse from(final Long productId, final String productName, final ProductStatus productStatus, final int productCount) {
        return new ProductResponse(productId, productName, productStatus, productCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductResponse that = (ProductResponse) o;
        return productCount == that.productCount && Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && productStatus == that.productStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productStatus, productCount);
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType=" + productStatus +
                ", productCount=" + productCount +
                '}';
    }
}
