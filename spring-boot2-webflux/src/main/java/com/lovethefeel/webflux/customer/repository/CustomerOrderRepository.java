package com.lovethefeel.webflux.customer.repository;

import com.lovethefeel.webflux.customer.domain.Customer;
import com.lovethefeel.webflux.customer.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
}
