package com.order.perf.domain.repository;

import com.order.perf.domain.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {
}
