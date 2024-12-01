package team.sipe.commerce.order.order.domain;

public interface OrderRepository {
    Order save(Order order);

    Order findById(Long orderId);
}
