package com.lovethefeel.webflux.product.repository;

import com.lovethefeel.webflux.product.domain.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryService {

    private final ProductRepository productRepository;

    public Product save(final Product product) {

        try {
            return productRepository.save(product);
        } catch (CannotCreateTransactionException ce) {
            log.error("[데이터베이스저장] Product: {}", product);
            log.error("[데이터베이스저장] Exception", ce);
            throw new RuntimeException("데이터베이스 저장에 실패하였습니다.");
        }
    }
}
