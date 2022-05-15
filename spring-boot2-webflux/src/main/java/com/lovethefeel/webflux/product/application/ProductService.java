package com.lovethefeel.webflux.product.application;

import com.lovethefeel.webflux.product.domain.Product;
import com.lovethefeel.webflux.product.dto.ProductRequest;
import com.lovethefeel.webflux.product.dto.ProductResponse;
import com.lovethefeel.webflux.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    final ProductRepository productRepository;

    @Transactional
    public ProductResponse saveProduct(final ProductRequest productRequest) {
        log.info("saveUser - productRequest [{}]", productRequest);
        final Product saveProduct = productRepository.save(productRequest.toEntity());
        ProductResponse productResponse = ProductResponse.of(saveProduct);
        log.info("saveUser - productResponse [{}]", productResponse);
        return productResponse;
    }
}
