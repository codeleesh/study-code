package me.lovethefeel.jpahistory.product.repository;

import me.lovethefeel.jpahistory.product.domain.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
}
