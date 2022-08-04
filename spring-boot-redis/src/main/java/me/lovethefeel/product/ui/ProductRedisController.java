package me.lovethefeel.product.ui;

import lombok.RequiredArgsConstructor;
import me.lovethefeel.product.application.ProductRedisService;
import me.lovethefeel.product.domain.ProductRedis;
import me.lovethefeel.product.dto.ProductRedisRequest;
import me.lovethefeel.product.dto.ProductRedisResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductRedisController {

    private final ProductRedisService productRedisService;

    @PostMapping("item")
    public ResponseEntity<ProductRedisResponse> saveProductItem(@RequestBody final ProductRedisRequest request) {

        final ProductRedis saveProductRedis = productRedisService.saveProductItem(request.toEntity());
        final ProductRedisResponse productRedisResponse = ProductRedisResponse.success(saveProductRedis);

        return ResponseEntity.status(HttpStatus.OK).body(productRedisResponse);
    }

    @GetMapping("item")
    public ResponseEntity<ProductRedisResponse> findProductItem(@RequestParam(name = "uuid") final String productUuid) {

        final ProductRedis findProductRedis = productRedisService.findProductItem(productUuid);
        final ProductRedisResponse productRedisResponse = ProductRedisResponse.success(findProductRedis);

        return ResponseEntity.status(HttpStatus.OK).body(productRedisResponse);
    }

    @DeleteMapping("item")
    public ResponseEntity<Void> deleteProductItem(@RequestParam(name = "uuid") final String productUuid) {

        productRedisService.deleteProductItem(productUuid);

        return ResponseEntity.noContent().build();
    }

}
