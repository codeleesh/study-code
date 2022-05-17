package com.lovethefeel.webflux.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
public class ProductRequest {

    @NotBlank(message = "상품명은 입력해야 합니다.")
    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_amount")
    private BigDecimal productAmount;

    @JsonProperty("product_status")
    private ProductStatus productStatus;

    @JsonProperty("product_count")
    private int productCount;

    public static ProductRequest from(final String productName, final BigDecimal productAmount, final ProductStatus productStatus, final int productCount) {
        return new ProductRequest(productName, productAmount, productStatus, productCount);
    }

    public Product toEntity() {
        return Product.from(this.productName, this.productAmount, this.productStatus, this.productCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest that = (ProductRequest) o;
        return productCount == that.productCount && Objects.equals(productName, that.productName) && Objects.equals(productAmount, that.productAmount) && productStatus == that.productStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productAmount, productStatus, productCount);
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", productStatus=" + productStatus +
                ", productCount=" + productCount +
                '}';
    }
}
