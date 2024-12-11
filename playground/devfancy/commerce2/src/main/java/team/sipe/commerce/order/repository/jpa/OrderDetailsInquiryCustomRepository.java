package team.sipe.commerce.order.repository.jpa;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import team.sipe.commerce.order.OrderDetailsInquiry;

import java.util.Date;

@Repository
public class OrderDetailsInquiryCustomRepository {

    private final MongoTemplate mongoTemplate;

    public OrderDetailsInquiryCustomRepository(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public OrderDetailsInquiry findByOrderId(final Long orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
        return mongoTemplate.findOne(query, OrderDetailsInquiry.class);
    }

    public void upsert(final OrderDetailsInquiry orderDetailsInquiry) {
        Query query = new Query(Criteria.where("orderId").is(orderDetailsInquiry.getOrderId()));
        Update update = new Update()
                .set("order_details_inquiry_info", orderDetailsInquiry.getOrderDetailsInquiryInfo())
                .set("updatedAt", new Date());

        mongoTemplate.upsert(query, update, OrderDetailsInquiry.class);
    }
}
