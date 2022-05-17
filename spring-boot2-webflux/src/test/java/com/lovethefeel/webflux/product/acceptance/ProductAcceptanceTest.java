package com.lovethefeel.webflux.product.acceptance;

import com.lovethefeel.webflux.AcceptanceTest;
import com.lovethefeel.webflux.fixture.TestProductFactory;
import com.lovethefeel.webflux.fixture.TestUserFactory;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@DisplayName("사용자 관련 인수 테스트")
class ProductAcceptanceTest extends AcceptanceTest {

    @DisplayName("상품 등록한다.")
    @Test
    void saveProduct() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);

        final ExtractableResponse<Response> 상품_응답 = TestProductFactory.상품_등록_요청함(상품_요청);

        TestProductFactory.상품_등록_생성됨(상품_응답);
    }
}
