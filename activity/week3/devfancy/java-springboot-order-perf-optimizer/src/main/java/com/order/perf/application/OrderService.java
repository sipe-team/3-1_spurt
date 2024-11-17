package com.order.perf.application;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.repository.DeliveryRepository;
import com.order.perf.domain.Order;
import com.order.perf.domain.repository.OrderRepository;
import com.order.perf.domain.Product;
import com.order.perf.domain.repository.ProductRepository;
import com.order.perf.domain.Refund;
import com.order.perf.domain.repository.RefundRepository;
import com.order.perf.application.dto.OrderDetailsResponse;
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
