package me.lovethefeel.jpahistory.product.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.jpahistory.product.application.ProductService;
import me.lovethefeel.jpahistory.product.dto.ProductRequest;
import me.lovethefeel.jpahistory.product.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        final ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.created(URI.create("/v1/product/" + productResponse.getProductId())).body(productResponse);
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest) {

        final ProductResponse productResponse = productService.updateProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
