package com.order.perf.application;

import com.order.perf.common.async.AsyncRepositoryService;
import com.order.perf.domain.*;
import com.order.perf.domain.repository.*;
import com.order.perf.application.dto.OrderDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

@Slf4j
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final AsyncRepositoryService asyncRepositoryService;
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    public OrderService(final AsyncRepositoryService asyncRepositoryService,
                        final OrderRepository orderRepository,
                        final DeliveryRepository deliveryRepository,
                        final RefundRepository refundRepository) {
        this.asyncRepositoryService = asyncRepositoryService;
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
        this.refundRepository = refundRepository;
    }

    public OrderDetailsResponse findOrderDetailsByAsync(final Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CompletableFuture<Delivery> deliveryFuture = asyncRepositoryService.findDeliveryByIdAsync(order.getDeliveryId());
        CompletableFuture<Refund> refundFuture = asyncRepositoryService.findRefundByIdAsync(order.getRefundId());
        CompletableFuture<List<OrderProduct>> orderProductsFuture = asyncRepositoryService.findOrderProductsAsync(order);

        // 모든 작업 완료 대기
        CompletableFuture.allOf(deliveryFuture, refundFuture, orderProductsFuture).join();

        return new OrderDetailsResponse(orderProductsFuture.join(), deliveryFuture.join(), refundFuture.join());
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

