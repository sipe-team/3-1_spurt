package com.order.perf.common.shard;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisShardRoutingService {

    private final String SHARD_DISTRIBUTION_KEY = "shard:distribution";

    private final StringRedisTemplate redisTemplate;

    public RedisShardRoutingService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // 샤드 분산률 조회
    public Map<Integer, Integer> getShardDistribution() {
        Map<Object, Object> rawDistribution = redisTemplate.opsForHash().entries(SHARD_DISTRIBUTION_KEY);
        return rawDistribution.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.getKey().toString()),
                        entry -> Integer.parseInt(entry.getValue().toString())
                ));
    }

    // 샤드 분산률 증가
    public void incrementShardDistribution(int shardId) {
        redisTemplate.opsForHash().increment(SHARD_DISTRIBUTION_KEY, String.valueOf(shardId), 1);
    }

    // 샤드 분산률 감소
    public void decrementShardDistribution(int shardId) {
        redisTemplate.opsForHash().increment(SHARD_DISTRIBUTION_KEY, String.valueOf(shardId), -1);
    }

    // 가장 적게 분산된 샤드 ID 가져오기
    public int getOptimalShardId() {
        Map<Integer, Integer> distribution = getShardDistribution();
        return distribution.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("No shard distribution available"))
                .getKey();
    }
}
