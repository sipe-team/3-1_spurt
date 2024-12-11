package team.sipe.commerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sipe.commerce.order.Order;
import team.sipe.commerce.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByOrder(final Order order);
}
