package com.order.perf.domain.repository;

import com.order.perf.domain.join.OrderJoin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderJoinRepository extends JpaRepository<OrderJoin, Long> {
}
