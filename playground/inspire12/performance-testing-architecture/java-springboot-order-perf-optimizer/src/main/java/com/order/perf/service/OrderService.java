package com.order.perf.service;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import com.order.perf.domain.join.OrderJoin;
import com.order.perf.domain.repository.DeliveryRepository;
import com.order.perf.domain.repository.OrderJoinRepository;
import com.order.perf.domain.repository.OrderRepository;
import com.order.perf.domain.repository.ProductRepository;
import com.order.perf.domain.repository.RefundRepository;
import com.order.perf.dto.OrderItemDto;
import com.order.perf.dto.response.OrderDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {
    private final OrderJoinRepository orderJoinRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    public OrderDetailsResponse getOrderDetailsResponse(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CompletableFuture<List<Product>> productsCompleteFuture = CompletableFuture.supplyAsync(() -> productRepository.findByOrder(order));

        CompletableFuture<Delivery> deliveryCompleteFuture = CompletableFuture.supplyAsync(() -> deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found")));
        CompletableFuture<Refund> refundCompleteFuture = CompletableFuture.supplyAsync(() -> refundRepository.findById(order.getRefundId())
                .orElseThrow(() -> new RuntimeException("Refund not found")));

//        CompletableFuture.allOf(productsCompleteFuture, deliveryCompleteFuture, refundCompleteFuture).join();
        List<Product> products = productsCompleteFuture.join();
        Delivery delivery = deliveryCompleteFuture.join();
        Refund refund = refundCompleteFuture.join();

        List<OrderItemDto> orderItemDto = products.stream()
                .map(product -> OrderItemDto.from(product, delivery, refund))
                .collect(Collectors.toList());
        return new OrderDetailsResponse(orderItemDto);
    }

    public OrderDetailsResponse getOrderByJoinDetailsResponse(Long orderId) {
        OrderJoin orderJoin = orderJoinRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemDto> orderItemDto = orderJoin.getProducts().stream()
          .map(product -> OrderItemDto.from(product, orderJoin.getDelivery(), orderJoin.getRefund()))
          .collect(Collectors.toList());
        return new OrderDetailsResponse(orderItemDto);
    }
}
