package com.order.perf.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "shard")
public class ShardConfig {

    private List<ShardInfo> config;

    public List<ShardInfo> getConfig() {
        return config;
    }

    public void setConfig(List<ShardInfo> config) {
        this.config = config;
    }

    @Bean
    public Map<Integer, DatabaseClient> databaseClients() {
        Map<Integer, DatabaseClient> clientMap = new HashMap<>();

        // 샤드 구성 정보를 기반으로 DatabaseClient 생성
        for (ShardInfo shardInfo : config) {
            clientMap.put(shardInfo.getId(), createDatabaseClient(shardInfo.getUrl()));
        }

        return clientMap;
    }

    private DatabaseClient createDatabaseClient(String url) {
        ConnectionFactory connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.parse(url));
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

    // 샤드 정보 모델 클래스
    public static class ShardInfo {
        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}