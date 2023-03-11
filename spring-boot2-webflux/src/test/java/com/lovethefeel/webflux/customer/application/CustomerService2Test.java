package com.lovethefeel.webflux.customer.application;

import com.lovethefeel.webflux.customer.domain.Customer;
import com.lovethefeel.webflux.customer.domain.CustomerOrder;
import com.lovethefeel.webflux.customer.repository.CustomerOrderRepository;
import com.lovethefeel.webflux.customer.repository.CustomerRepository;
import com.lovethefeel.webflux.product.application.ProductService;
import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("고객 관련 서비스 테스트")
class CustomerService2Test {

    @Test
    void 메시지전송이_실패한다() {

        final Customer customer = new Customer();

        CustomerRepository customerRepository = new MockCustomerRepository();
        CustomerOrderRepository customerOrderRepository = new MockCustomerOrderRepository();
        ProductRepository productRepository = new MockProductRepository();
        ProductService productService = new MockProductService(productRepository);
        RabbitTemplate rabbitTemplate = new MockRabbitTemplate();
        CustomerService customerService = new CustomerService(customerRepository, customerOrderRepository, productService, rabbitTemplate);

        assertThatThrownBy(() -> customerService.sendMessage(customer))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("메세지전송 호출에 실패하였습니다.");
    }

    private class MockRabbitTemplate extends RabbitTemplate {

        @Override
        public void convertAndSend(String exchange, String routingKey, final Object object) {
            throw new RuntimeException("메세지전송 호출에 실패하였습니다.");
        }
    }

    private class MockProductService extends ProductService {

        public MockProductService(ProductRepository productRepository) {
            super(productRepository);
        }
    }

    private class MockCustomerRepository implements CustomerRepository {
        @Override
        public List<Customer> findAll() {
            return null;
        }

        @Override
        public List<Customer> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Customer> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Customer> findAllById(Iterable<Long> longs) {
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
        public void delete(Customer entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Customer> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Customer> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<Customer> findById(Long aLong) {
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
        public <S extends Customer> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Customer> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Customer getOne(Long aLong) {
            return null;
        }

        @Override
        public Customer getById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Customer> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Customer> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Customer> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Customer> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    }

    private class MockCustomerOrderRepository implements CustomerOrderRepository {
        @Override
        public List<CustomerOrder> findAll() {
            return null;
        }

        @Override
        public List<CustomerOrder> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<CustomerOrder> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<CustomerOrder> findAllById(Iterable<Long> longs) {
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
        public void delete(CustomerOrder entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends CustomerOrder> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends CustomerOrder> S save(S entity) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<CustomerOrder> findById(Long aLong) {
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
        public <S extends CustomerOrder> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<CustomerOrder> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public CustomerOrder getOne(Long aLong) {
            return null;
        }

        @Override
        public CustomerOrder getById(Long aLong) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends CustomerOrder> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends CustomerOrder> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends CustomerOrder> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends CustomerOrder, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    }

    private class MockProductRepository implements ProductRepository {
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
            return null;
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