package team.sipe.commerce.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sipe.commerce.delivery.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
