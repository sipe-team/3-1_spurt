package com.order.perf.common.shard;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShardRoutingService {

    private final RedisShardRoutingService redisShardRoutingService;
    private final Map<Integer, DatabaseClient> shardClients;

    public ShardRoutingService(RedisShardRoutingService redisShardRoutingService, Map<Integer, DatabaseClient> shardClients) {
        this.redisShardRoutingService = redisShardRoutingService;
        this.shardClients = shardClients;
    }

    // 최적의 샤드 클라이언트 결정
    public DatabaseClient resolveShard() {
        int shardId = redisShardRoutingService.getOptimalShardId();
        return shardClients.get(shardId);
    }

    // 샤드 클라이언트에서 샤드 ID 가져오기
    public int getShardIdForClient(DatabaseClient client) {
        return shardClients.entrySet().stream()
                .filter(entry -> entry.getValue().equals(client))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Shard client not found"))
                .getKey();
    }

    // 샤드 ID로 클라이언트 반환
    public DatabaseClient getClientForShard(int shardId) {
        if (!shardClients.containsKey(shardId)) {
            throw new IllegalArgumentException("Invalid shard ID: " + shardId);
        }
        return shardClients.get(shardId);
    }

    // 샤드 분산률 증가
    public void incrementShardDistribution(int shardId) {
        redisShardRoutingService.incrementShardDistribution(shardId);
    }

    // 샤드 분산률 감소
    public void decrementShardDistribution(int shardId) {
        redisShardRoutingService.decrementShardDistribution(shardId);
    }
}