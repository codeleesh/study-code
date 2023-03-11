package com.lovethefeel.webflux.customer.repository;

import com.lovethefeel.webflux.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
