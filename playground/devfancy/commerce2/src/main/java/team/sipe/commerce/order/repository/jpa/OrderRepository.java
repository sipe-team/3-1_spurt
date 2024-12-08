package team.sipe.commerce.order.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sipe.commerce.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
