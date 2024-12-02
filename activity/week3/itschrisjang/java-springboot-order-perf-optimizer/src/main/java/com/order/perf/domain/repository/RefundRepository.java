package com.order.perf.domain.repository;

import com.order.perf.domain.Refund;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RefundRepository extends ReactiveCrudRepository<Refund, Long> {
}
