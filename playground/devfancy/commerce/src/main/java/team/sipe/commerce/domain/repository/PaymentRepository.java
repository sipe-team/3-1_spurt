package team.sipe.commerce.domain.repository;

import team.sipe.commerce.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
