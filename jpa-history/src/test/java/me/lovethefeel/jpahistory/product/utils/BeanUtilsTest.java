package me.lovethefeel.jpahistory.product.utils;

import me.lovethefeel.jpahistory.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanUtilsTest {

    @DisplayName("원하는 빈을 찾을 수 있습니다.")
    @Test
    void success_get_bean() {

        final ProductRepository productRepository = (ProductRepository) BeanUtils.getBean(ProductRepository.class);

        assertThat(productRepository).isNotNull();
    }
}