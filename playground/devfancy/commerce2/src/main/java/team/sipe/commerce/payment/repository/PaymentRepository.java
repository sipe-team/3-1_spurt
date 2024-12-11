package team.sipe.commerce.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sipe.commerce.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
