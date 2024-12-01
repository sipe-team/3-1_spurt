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
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    @Cacheable(value = "orders", key = "#orderId")
    public OrderDetailsResponse findOrderDetails(final Long orderId) throws ExecutionException, InterruptedException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CompletableFuture<List<Product>> productsFuture = CompletableFuture.supplyAsync(()-> productRepository.findByOrder(order));
        CompletableFuture<Delivery> deliveryFuture = CompletableFuture.supplyAsync(() -> deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found")));
        CompletableFuture<Refund> refundFuture = CompletableFuture.supplyAsync(() -> refundRepository.findById(order.getRefundId())
                .orElseThrow(()-> new RuntimeException("Refund not found")));

        CompletableFuture.allOf(productsFuture, deliveryFuture, refundFuture).join();

        return new OrderDetailsResponse(productsFuture.get(), deliveryFuture.get(), refundFuture.get());
    }
}
