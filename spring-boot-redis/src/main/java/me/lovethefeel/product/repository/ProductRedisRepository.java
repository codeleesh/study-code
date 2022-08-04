package me.lovethefeel.product.repository;

import me.lovethefeel.product.domain.ProductRedis;
import org.springframework.data.repository.CrudRepository;

public interface ProductRedisRepository extends CrudRepository<ProductRedis, String> {
}
