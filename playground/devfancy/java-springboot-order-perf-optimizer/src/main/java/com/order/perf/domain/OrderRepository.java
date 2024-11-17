package com.order.perf.domain;

import org.springframework.data.jpa.repository.JpaRepository;


interface OrderRepository extends JpaRepository<Order, Long> {
}
