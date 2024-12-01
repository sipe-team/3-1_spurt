package team.sipe.commerce.order.order.application;

import org.springframework.stereotype.Component;
import team.sipe.commerce.order.order.application.command.OrderCreateCommand;
import team.sipe.commerce.order.order.domain.Order;
import team.sipe.commerce.order.order.domain.OrderRepository;
import team.sipe.commerce.order.order.domain.product.OrderProduct;

@Component
public class OrderService implements OrderCreateUseCase {

    private final OrderProductClient orderProductClient;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    public OrderService(final OrderProductClient orderProductClient,
                        final OrderRepository orderRepository,
                        final OrderFactory orderFactory) {
        this.orderProductClient = orderProductClient;
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    @Override
    public Long create(final OrderCreateCommand command) {
        final OrderProduct orderProduct = orderProductClient.findByProductId(command.shopId(), command.productId());
        final Order order = orderRepository.create(orderFactory.init(command, orderProduct));
        return order.getId();
    }
}
