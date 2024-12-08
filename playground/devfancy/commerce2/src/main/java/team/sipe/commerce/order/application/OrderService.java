package team.sipe.commerce.order.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import team.sipe.commerce.delivery.Delivery;
import team.sipe.commerce.delivery.repository.DeliveryRepository;
import team.sipe.commerce.order.Order;
import team.sipe.commerce.order.OrderDetailsInquiry;
import team.sipe.commerce.order.repository.jpa.OrderDetailsInquiryCustomRepository;
import team.sipe.commerce.order.repository.mongo.OrderDetailsInquiryRepository;
import team.sipe.commerce.order.repository.jpa.OrderRepository;
import team.sipe.commerce.product.Product;
import team.sipe.commerce.product.repository.ProductRepository;
import team.sipe.commerce.refund.Refund;
import team.sipe.commerce.order.application.dto.OrderDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sipe.commerce.refund.repository.RefundRepository;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final RefundRepository refundRepository;
    // [1] MongoDB query method
    private final OrderDetailsInquiryRepository orderDetailsInquiryRepository;
    // [2] MongoTemplate
    private final OrderDetailsInquiryCustomRepository orderDetailsInquiryCustomRepository;
    private final ObjectMapper objectMapper;

    public OrderService(final OrderRepository orderRepository,
                        final ProductRepository productRepository,
                        final DeliveryRepository deliveryRepository,
                        final RefundRepository refundRepository,
                        final OrderDetailsInquiryRepository orderDetailsInquiryRepository,
                        final OrderDetailsInquiryCustomRepository orderDetailsInquiryCustomRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
        this.refundRepository = refundRepository;
        this.orderDetailsInquiryRepository = orderDetailsInquiryRepository;
        this.orderDetailsInquiryCustomRepository = orderDetailsInquiryCustomRepository;
        this.objectMapper = new ObjectMapper(); // reuse
    }

    // h2, mysql
    public OrderDetailsResponse findOrderDetails(final Long orderId) {
        log.info("Fetching order details for orderId={}", orderId);

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<Product> products = productRepository.findByOrder(order);

        Delivery delivery = deliveryRepository.findById(order.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        Refund refund = refundRepository.findById(order.getRefundId())
                .orElseThrow(() -> new RuntimeException("Refund not found"));

        log.info("Successfully fetched order details for orderId={}", orderId);
        return new OrderDetailsResponse(products, delivery, refund);
    }

    // mongodb
    public OrderDetailsResponse findOrderDetailsWithSnapshot(final Long orderId) {
        // [1] MongoDB query method
//        OrderDetailsInquiry orderDetailsInquiry = orderDetailsInquiryRepository.findByOrderId(orderId)
//                .orElse(null);

        // [2] MongoTemplate
        OrderDetailsInquiry orderDetailsInquiry2 = orderDetailsInquiryCustomRepository.findByOrderId(orderId);

        if (orderDetailsInquiry2 != null) {
            log.info("Snapshot exists for orderId={}", orderId);
            try {
                return objectMapper.readValue(orderDetailsInquiry2.getOrderDetailsInquiryInfo(), OrderDetailsResponse.class);
            } catch (JsonProcessingException e) {
                log.error("Failed to parse snapshot JSON for orderId={}", orderId, e.getMessage());
                throw new RuntimeException("Failed to parse Order Snapshot JSON");
            }
        }

        log.info("No snapshot found for orderId={} querying H2/MySQL", orderId);
        OrderDetailsResponse response = findOrderDetails(orderId);

        createOrUpdateOrderSnapshot(orderId, response);
        log.info("Snapshot created for orderId={}", orderId);
        return response;
    }

    @Transactional
    public void createOrUpdateOrderSnapshot(final Long orderId, final OrderDetailsResponse orderDetailsResponse) {
        log.info("Creating or updating snapshot for orderId={}", orderId);
        try {
            String orderDetailsInquiryInfo = objectMapper.writeValueAsString(orderDetailsResponse);

            OrderDetailsInquiry orderDetailsInquiry = orderDetailsInquiryRepository.findByOrderId(orderId)
                    .orElse(new OrderDetailsInquiry(orderId, orderDetailsInquiryInfo));

            orderDetailsInquiryCustomRepository.upsert(orderDetailsInquiry);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize OrderDetailsResponse for orderId={}", orderId, e.getMessage());
            throw new RuntimeException("Failed to serialize OrderDetailsResponse to JSON");
        }
    }
}
