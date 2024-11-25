package team.sipe.commerce.domain.repository;

import team.sipe.commerce.domain.Order;
import team.sipe.commerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrder(final Order order);
}
