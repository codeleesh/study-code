package me.lovethefeel.jpahistory.product.application;

import me.lovethefeel.jpahistory.product.domain.Product;
import me.lovethefeel.jpahistory.product.domain.ProductHistory;
import me.lovethefeel.jpahistory.product.dto.ProductRequest;
import me.lovethefeel.jpahistory.product.dto.ProductResponse;
import me.lovethefeel.jpahistory.product.repository.ProductHistoryRepository;
import me.lovethefeel.jpahistory.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductHistoryRepository productHistoryRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 등록한다.")
    @Test
    void saveProduct() {

        final Timestamp currentTimestamp = Timestamp.valueOf("2022-08-12 22:10:00.000");
        final ProductRequest 상품_요청 = ProductRequest.of(1L, "치킨");
        final Product 상품_생성_기대값 = Product.of(1L, "치킨", currentTimestamp, "SYSTEM", currentTimestamp, "SYSTEM");
        final ProductHistory 상품_이력_생성_기대값 = ProductHistory.fromEntity(상품_생성_기대값, "신규 저장");

        given(productRepository.save(any())).willReturn(상품_생성_기대값);

        final ProductResponse 상품_생성_응답값 = productService.createProduct(상품_요청);

        verify(productRepository, times(1)).save(any());
    }

}