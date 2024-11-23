package team.sipe.commerce.application;

import team.sipe.commerce.domain.Delivery;
import team.sipe.commerce.domain.repository.DeliveryRepository;
import team.sipe.commerce.domain.Order;
import team.sipe.commerce.domain.repository.OrderRepository;
import team.sipe.commerce.domain.Product;
import team.sipe.commerce.domain.repository.ProductRepository;
import team.sipe.commerce.domain.Refund;
import team.sipe.commerce.domain.repository.RefundRepository;
import team.sipe.commerce.application.dto.OrderDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    public OrderService(final OrderRepository orderRepository,
                        final ProductRepository productRepository,
                        final DeliveryRepository deliveryRepository,
                        final RefundRepository refundRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
        this.refundRepository = refundRepository;
    }

    public OrderDetailsResponse findOrderDetails(final Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<Product> products = productRepository.findByOrder(order);

        Delivery delivery = deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        Refund refund = refundRepository.findById(order.getRefundId())
                .orElseThrow(() -> new RuntimeException("Refund not found"));

        return new OrderDetailsResponse(products, delivery, refund);
    }
}
