package team.sipe.commerce.global.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Objects;


@Configuration
@EnableJpaRepositories(
        basePackages = {
                "team.sipe.commerce.order.repository.jpa",
                "team.sipe.commerce.delivery.repository",
                "team.sipe.commerce.payment.repository",
                "team.sipe.commerce.product.repository",
                "team.sipe.commerce.refund.repository"
        }// JPA 관련 리포지토리 경로 설정
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "transactionManager"
)
@EnableMongoRepositories(
        basePackages = "team.sipe.commerce.order.repository.mongo" // MongoDB 관련 리포지토리 경로 설정
)
public class DataSourceConfig {

    private final Environment env;

    public DataSourceConfig(final Environment env) {
        this.env = env;
    }

    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory() {
        String uri = env.getProperty("spring.data.mongodb.uri");
        if (uri == null || uri.isEmpty()) {
            String host = env.getProperty("spring.data.mongodb.host");
            String port = env.getProperty("spring.data.mongodb.port");
            String database = env.getProperty("spring.data.mongodb.database");
            String username = env.getProperty("spring.data.mongodb.username");
            String password = env.getProperty("spring.data.mongodb.password");

            StringBuilder uriBuilder = new StringBuilder("mongodb://");
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                uriBuilder.append(username).append(":").append(password).append("@");
            }
            uriBuilder.append(host).append(":").append(port).append("/").append(database);
            uri = uriBuilder.toString();
        }
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(uri), Objects.requireNonNull(env.getProperty("spring.data.mongodb.database")));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
