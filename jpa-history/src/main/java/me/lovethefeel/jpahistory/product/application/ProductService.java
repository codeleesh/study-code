package me.lovethefeel.jpahistory.product.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lovethefeel.jpahistory.product.domain.Product;
import me.lovethefeel.jpahistory.product.dto.ProductRequest;
import me.lovethefeel.jpahistory.product.dto.ProductResponse;
import me.lovethefeel.jpahistory.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(final ProductRequest productRequest) {

        final Product saveProduct = productRepository.save(productRequest.toCreateEntity());

        return ProductResponse.fromResponse(saveProduct);
    }

    @Transactional
    public ProductResponse updateProduct(final ProductRequest productRequest) {

        final Product findMember = productRepository.findById(productRequest.getProductId())
                .orElseThrow(IllegalArgumentException::new);

        findMember.updateName(productRequest.getProductName());

        return ProductResponse.fromResponse(findMember);
    }
}
