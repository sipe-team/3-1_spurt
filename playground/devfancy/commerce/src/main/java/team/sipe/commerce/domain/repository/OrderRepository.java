package team.sipe.commerce.domain.repository;

import team.sipe.commerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
