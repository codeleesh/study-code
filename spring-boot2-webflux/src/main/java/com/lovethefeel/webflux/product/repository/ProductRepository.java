package com.lovethefeel.webflux.product.repository;

import com.lovethefeel.webflux.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
