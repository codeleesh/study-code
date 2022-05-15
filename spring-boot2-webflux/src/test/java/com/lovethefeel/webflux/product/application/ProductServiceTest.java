package com.lovethefeel.webflux.product.application;

import com.lovethefeel.webflux.fixture.TestProductFactory;
import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import com.lovethefeel.webflux.product.dto.ProductResponse;
import com.lovethefeel.webflux.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("상품 생성 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 등록한다.")
    @Test
    void saveProduct() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("치킨", ProductStatus.ENABLE, 10);
        final Product 상품_생성_기대값 = TestProductFactory.상품(1L, "치킨", ProductStatus.ENABLE, 10);

        given(productRepository.save(any())).willReturn(상품_생성_기대값);

        final ProductResponse 상품_생성_응답값 = productService.saveProduct(상품_요청);

        TestProductFactory.상품_등록_생성됨(상품_생성_응답값, 상품_생성_기대값);
    }

}
