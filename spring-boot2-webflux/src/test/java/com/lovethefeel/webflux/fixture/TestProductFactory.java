package com.lovethefeel.webflux.fixture;

import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import com.lovethefeel.webflux.product.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class TestProductFactory {
    public static Product 상품(final Long productId, final String productName, final BigDecimal productAmount, final ProductStatus productStatus, final int productCount) {
        return Product.from(productId, productName, productAmount, productStatus, productCount);
    }

    public static ProductRequest 상품_등록_요청(final String productName, final BigDecimal productAmount, final ProductStatus productStatus, final int productCount) {
        return ProductRequest.from(productName, productAmount, productStatus, productCount);
    }

    public static void 상품_등록_생성됨(ProductResponse actual, Product expected) {
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getProductId()).isEqualTo(expected.getId()),
                () -> assertThat(actual.getProductName()).isEqualTo(expected.getName()),
                () -> assertThat(actual.getProductAmount()).isEqualTo(expected.getAmount()),
                () -> assertThat(actual.getProductStatus()).isEqualTo(expected.getStatus()),
                () -> assertThat(actual.getProductCount()).isEqualTo(expected.getCount())
        );
    }

    public static ProductResponse 상품_등록_응답(Long productId, String productName, BigDecimal productAmount, ProductStatus productStatus, int productCount) {
        return ProductResponse.from(productId, productName, productAmount, productStatus, productCount);
    }

    public static ExtractableResponse<Response> 상품_등록_요청함(ProductRequest 상품_요청) {
        return RestAssured
                .given().log().all()
                .body(상품_요청)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/product")
                .then().log().all()
                .extract();
    }

    public static void 상품_등록_생성됨(ExtractableResponse<Response> 상품_응답) {
        assertThat(상품_응답.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
