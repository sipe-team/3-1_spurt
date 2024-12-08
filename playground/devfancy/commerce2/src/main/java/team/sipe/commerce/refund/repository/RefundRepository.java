package team.sipe.commerce.refund.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sipe.commerce.refund.Refund;

public interface RefundRepository extends JpaRepository<Refund, Long> {
}
