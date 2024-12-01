package com.order.perf.repository;

import com.order.perf.domain.Refund;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, Long> {

  Optional<Refund> findByOrderId(Long orderId);
}
