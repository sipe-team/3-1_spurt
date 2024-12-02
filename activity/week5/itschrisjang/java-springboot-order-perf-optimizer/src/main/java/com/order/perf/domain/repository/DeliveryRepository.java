package com.order.perf.domain.repository;

import com.order.perf.domain.Delivery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DeliveryRepository extends ReactiveCrudRepository<Delivery, Long> {
}
