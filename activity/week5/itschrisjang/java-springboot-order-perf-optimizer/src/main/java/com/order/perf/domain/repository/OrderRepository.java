package com.order.perf.domain.repository;

import com.order.perf.domain.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

    // 사용자 ID로 주문 조회
    @Query("SELECT * FROM orders WHERE user_id = :userId")
    Flux<Order> findByUserId(String userId);

    // 특정 샤드 ID로 주문 조회
    @Query("SELECT * FROM orders WHERE shard_id = :shardId")
    Flux<Order> findByShardId(String shardId);

}

