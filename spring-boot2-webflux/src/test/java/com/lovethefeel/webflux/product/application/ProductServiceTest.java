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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.transaction.CannotCreateTransactionException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("상품 생성 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품을 등록한다.")
    @Test
    void saveProduct() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);
        final Product 상품_생성_기대값 = TestProductFactory.상품(1L, "치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);

        given(productRepository.save(any())).willReturn(상품_생성_기대값);

        final ProductResponse 상품_생성_응답값 = productService.saveProduct(상품_요청);

        TestProductFactory.상품_등록_생성됨(상품_생성_응답값, 상품_생성_기대값);
    }

    @DisplayName("데이터베이스 장애")
    @Test
    void error_connect_product() {
        final ProductRequest 상품_요청 = TestProductFactory.상품_등록_요청("치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);
        final Product 상품_생성_기대값 = TestProductFactory.상품(1L, "치킨", new BigDecimal("20000"), ProductStatus.ENABLE, 10);

        ProductRepository productRepository = new ProductRepository.Fake();

        assertThatThrownBy(() -> productRepository.save(상품_생성_기대값))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("데이터베이스가 죽었습니다.");
    }

    static class MockProductRepository implements ProductRepository {
        @Override
        public List<Product> findAll() {
            return null;
        }

        @Override
        public List<Product> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Product> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Product> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Product entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Product> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Product> S save(S entity) {
            throw new CannotCreateTransactionException("데이터베이스가 죽었습니다.");
        }

        @Override
        public <S extends Product> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Product> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Product> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Product> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Product getOne(Long aLong) {
            return null;
        }

        @Override
        public Product getById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Product> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Product> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Product> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Product> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    }

}
