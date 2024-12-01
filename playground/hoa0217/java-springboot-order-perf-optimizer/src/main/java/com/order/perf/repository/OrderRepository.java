package com.order.perf.repository;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
