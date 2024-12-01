package team.sipe.commerce.order.order.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.sipe.commerce.order.order.domain.Order;
import team.sipe.commerce.order.order.domain.OrderRepository;
import team.sipe.commerce.order.order.infrastructure.persistence.OrderJpaRepository;
import team.sipe.commerce.order.order.infrastructure.persistence.mapper.OrderEntityMapper;

import java.util.Optional;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(final OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Transactional
    @Override
    public Order save(final Order order) {
        return Optional.ofNullable(orderJpaRepository.save(OrderEntityMapper.toEntity(order)))
                .map(OrderEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Failed to create order"));
    }

    @Transactional(readOnly = true)
    @Override
    public Order findById(final Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(OrderEntityMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
