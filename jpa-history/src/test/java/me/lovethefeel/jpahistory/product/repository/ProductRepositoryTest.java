package me.lovethefeel.jpahistory.product.repository;

import me.lovethefeel.jpahistory.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private ProductHistoryRepository historyRepository;

    @Test
    @Transactional
    void create_member_and_history() {

        final Product product = Product.fromCreate("아이템");

        final Product saveProduct = productRepository.save(product);

        assertThat(saveProduct).isEqualTo(product);

        verify(historyRepository, times(1)).save(any());
    }
}
