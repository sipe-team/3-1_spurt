package com.order.perf.application;

import com.order.perf.domain.*;
import com.order.perf.domain.repository.*;
import com.order.perf.application.dto.OrderDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

@Slf4j
@Transactional(readOnly = true)
@Service
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

    public OrderDetailsResponse findOrderDetailsByAsync(final Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CompletableFuture<Delivery> deliveryCompletableFuture = CompletableFuture.supplyAsync(() -> deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found")));

        CompletableFuture<Refund> refundCompletableFuture = CompletableFuture.supplyAsync(() -> refundRepository.findById(order.getRefundId())
                .orElseThrow(() -> new RuntimeException("Refund not found")));

        CompletableFuture<List<OrderProduct>> orderProductsCompletableFuture
                = CompletableFuture.supplyAsync(() -> order.getOrderProducts());

        CompletableFuture.allOf(orderProductsCompletableFuture, deliveryCompletableFuture, refundCompletableFuture).join();

        return new OrderDetailsResponse(orderProductsCompletableFuture.join(), deliveryCompletableFuture.join(), refundCompletableFuture.join());
    }

    public OrderDetailsResponse findOrderDetailsBySync(final Long orderId) {
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

