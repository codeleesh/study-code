package com.lovethefeel.webflux.product.repository;

import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.domain.ProductStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("상품을 저장를 할 수 있다.")
    @Test
    void 사용자_저장() {
        final Product 테스트 = Product.from("치킨", ProductStatus.ENABLE, 10);

        final Product 테스트저장 = productRepository.save(테스트);

        final Product 테스트조회 = productRepository.findById(1L)
                .orElseThrow(IllegalArgumentException::new);

        assertThat(테스트저장).isEqualTo(테스트조회);
    }
}
