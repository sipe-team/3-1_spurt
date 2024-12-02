package com.order.perf.application;

import com.order.perf.application.dto.OrderDetailsResponse;
import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import com.order.perf.domain.repository.DeliveryRepository;
import com.order.perf.domain.repository.OrderRepository;
import com.order.perf.domain.repository.ProductRepository;
import com.order.perf.domain.repository.RefundRepository;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class OrderService {

    private final ReactiveRedisTemplate<String, Object> redisTemplate;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;

    public OrderService(ReactiveRedisTemplate<String, Object> redisTemplate, final OrderRepository orderRepository,
                        final ProductRepository productRepository,
                        final DeliveryRepository deliveryRepository,
                        final RefundRepository refundRepository) {
        this.redisTemplate = redisTemplate;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
        this.refundRepository = refundRepository;
    }

    public Mono<OrderDetailsResponse> findOrderDetailsWithCache(final Long orderId) {
        String cacheKey = "orderDetails:" + orderId;

        return redisTemplate.opsForValue()
                .get(cacheKey) // 1. Redis에서 데이터 조회
                .cast(OrderDetailsResponse.class) // 2. 데이터를 OrderDetailsResponse로 캐스팅
                .switchIfEmpty(
                        findOrderDetails(orderId) // 3. 캐시에 없을 경우 DB에서 데이터 조회
                                .flatMap(response ->
                                        redisTemplate.opsForValue()
                                                .set(cacheKey, response, Duration.ofMinutes(10)) // 4. 캐시에 저장 (10분 TTL 설정)
                                                .thenReturn(response) // 저장 후 DB 데이터를 반환
                                )
                );
    }

    private Mono<OrderDetailsResponse> findOrderDetails(final Long orderId) {
        Mono<Order> orderMono = orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Order not found")));

        Mono<Delivery> deliveryMono = orderMono.flatMap(order ->
                deliveryRepository.findById(order.getDeliveryId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Delivery not found")))
        );

        Mono<Refund> refundMono = orderMono.flatMap(order ->
                refundRepository.findById(order.getRefundId())
                        .switchIfEmpty(Mono.error(new RuntimeException("Refund not found")))
        );

        Mono<List<Product>> productsMono = productRepository.findAllById(orderId).collectList();

        return Mono.zip(productsMono, deliveryMono, refundMono)
                .map(tuple -> new OrderDetailsResponse(
                        tuple.getT1(), // products
                        tuple.getT2(), // delivery
                        tuple.getT3()  // refund
                ));
    }

}
