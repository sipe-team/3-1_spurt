package com.order.perf.repository;

import com.order.perf.domain.Delivery;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

  Optional<Delivery> findByOrderId(Long orderId);
}
