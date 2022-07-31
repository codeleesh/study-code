package me.lovethefeel.jpahistory.product.repository;

import me.lovethefeel.jpahistory.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
