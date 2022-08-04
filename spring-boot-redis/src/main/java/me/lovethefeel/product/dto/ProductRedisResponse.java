package me.lovethefeel.product.dto;

import lombok.Getter;
import me.lovethefeel.product.domain.ProductRedis;

@Getter
public class ProductRedisResponse {

    private boolean result;
    private String uuid;

    private ProductRedisResponse(final boolean result, final String uuid) {

        this.result = result;
        this.uuid = uuid;
    }

    public static ProductRedisResponse success(final String uuid) {

        return new ProductRedisResponse(true, uuid);
    }

    public static ProductRedisResponse success(final ProductRedis saveProductRedis) {

        return new ProductRedisResponse(true, saveProductRedis.getUuid());
    }
}
