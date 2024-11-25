package com.order.perf.application;

import com.order.perf.domain.*;
import com.order.perf.domain.repository.*;
import com.order.perf.application.dto.OrderDetailsResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@Async
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    public OrderService(final OrderRepository orderRepository,
                        final DeliveryRepository deliveryRepository,
                        final RefundRepository refundRepository) {
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.refundRepository = refundRepository;
    }

    public OrderDetailsResponse findOrderDetails(final Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        Delivery delivery = deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        Refund refund = refundRepository.findById(order.getRefundId())
                .orElseThrow(() -> new RuntimeException("Refund not found"));

        List<OrderProduct> orderProducts = order.getOrderProducts();

        return new OrderDetailsResponse(orderProducts, delivery, refund);
    }
}
