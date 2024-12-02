package com.order.perf.common.async;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import com.order.perf.domain.OrderProduct;
import com.order.perf.domain.Refund;
import com.order.perf.domain.repository.DeliveryRepository;
import com.order.perf.domain.repository.RefundRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
public class AsyncRepositoryService {
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    @Async
    public CompletableFuture<Delivery> findDeliveryByIdAsync(Long deliveryId) {
        log.info("DeliveryByIdAsync thread name: {}", Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found")));
    }

    @Async
    public CompletableFuture<Refund> findRefundByIdAsync(Long refundId) {
        log.info("RefundByIdAsync thread name: {}", Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> refundRepository.findById(refundId)
                .orElseThrow(() -> new RuntimeException("Refund not found")));
    }

    @Async
    public CompletableFuture<List<OrderProduct>> findOrderProductsAsync(Order order) {
        log.info("OrderProductsAsync thread name: {}", Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(order::getOrderProducts);
    }
}
