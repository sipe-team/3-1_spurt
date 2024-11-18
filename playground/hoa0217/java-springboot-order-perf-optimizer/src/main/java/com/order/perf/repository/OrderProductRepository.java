package com.order.perf.repository;

import com.order.perf.domain.OrderProduct;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

  Optional<OrderProduct> findByOrderId(Long orderId);
}
