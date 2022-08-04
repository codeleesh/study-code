package me.lovethefeel.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import me.lovethefeel.product.domain.ProductRedis;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Getter
@JsonInclude(NON_NULL)
public class ProductRedisRequest {

    private String uuid;
    private String productId;
    private String productName;
    private Integer productCount;

    protected ProductRedisRequest() {}

    public ProductRedis toEntity() {

        return ProductRedis.create(this.uuid, this.productId, this.productName, this.productCount);
    }
}
