package me.lovethefeel.product.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@RedisHash(value = "product-mapping-key", timeToLive = 30)
public class ProductRedis {

    @Id
    private String uuid;
    private String productId;
    private String productName;
    private Integer productCount;
    private LocalDateTime createdAt;

    protected ProductRedis() {}

    private ProductRedis(final String uuid, final String productId, final String productName, final Integer productCount) {

        this.uuid = uuid;
        this.productId = productId;
        this.productName = productName;
        this.productCount = productCount;
        this.createdAt = LocalDateTime.now();
    }

    public static ProductRedis create(final String uuid, final String productId, final String productName, final Integer productCount) {

        return new ProductRedis(uuid, productId, productName, productCount);
    }

}
