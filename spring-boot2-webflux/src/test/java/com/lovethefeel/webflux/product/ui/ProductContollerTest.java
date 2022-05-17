package com.lovethefeel.webflux.product.ui;

import com.lovethefeel.webflux.fixture.TestProductFactory;
import com.lovethefeel.webflux.product.application.ProductService;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import com.lovethefeel.webflux.product.dto.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(ProductController.class)
class ProductContollerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProductService productService;

    @DisplayName("상품 등록한다.")
    @Test
    void createProductTest() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);
        final ProductResponse 상품_응답 = TestProductFactory.상품_등록_응답(1L, "치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);

        given(this.productService.saveProduct(any()))
                .willReturn(상품_응답);

        this.webTestClient.post().uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(상품_요청))
                .exchange()
                .expectAll(
                        responseSpec -> responseSpec.expectStatus().isCreated(),
                        responseSpec -> responseSpec.expectHeader().contentType(MediaType.APPLICATION_JSON)
                )
                .expectBody()
                .jsonPath("$.product_id").isEqualTo(1L)
                .jsonPath("$.product_name").isEqualTo("치킨")
                .jsonPath("$.product_status").isEqualTo("ENABLE")
                .jsonPath("$.product_count").isEqualTo(10);
    }

    @DisplayName("상품 이름을 입력하지 않으면 예외를 발생한다.")
    @Test
    void createUserTest_validation() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("", new BigDecimal("20000"), ProductStatus.ENABLE, 10);

        this.webTestClient.post().uri("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(상품_요청))
                .exchange()
                .expectStatus().isBadRequest();
    }
}
