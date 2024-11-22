package com.order.perf.domain.repository;

import com.order.perf.domain.Order;
import com.order.perf.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrder(final Order order);
}
