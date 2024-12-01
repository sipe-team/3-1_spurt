package team.sipe.commerce.order.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import team.sipe.commerce.order.OrderDetailsInquiry;

import java.util.Optional;

public interface OrderDetailsInquiryRepository extends MongoRepository<OrderDetailsInquiry, String> {
    Optional<OrderDetailsInquiry> findByOrderId(final Long orderId);
}
