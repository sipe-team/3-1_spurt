package team.sipe.commerce.order;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import team.sipe.commerce.common.BaseEntity;

import javax.persistence.Id;

@Document(collection = "orderDetailsInquiry")
@CompoundIndex(name = "order_id_index", def = "{'orderId': 1}")
public class OrderDetailsInquiry extends BaseEntity {

    @Id
    private String id;

    private Long orderId;

    @Field("order_details_inquiry_info")
    private String orderDetailsInquiryInfo;


    public OrderDetailsInquiry() {
    }

    public OrderDetailsInquiry(final Long orderId,
                               final String orderDetailsInquiryInfo) {
        this.orderId = orderId;
        this.orderDetailsInquiryInfo = orderDetailsInquiryInfo;
    }

    public void updateOrderID(final Long orderId) {
        this.orderId = orderId;
    }

    public void updateOrderDetailsInquiryJson(final String orderDetailsInquiryInfo) {
        this.orderDetailsInquiryInfo = orderDetailsInquiryInfo;
    }

    public String getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getOrderDetailsInquiryInfo() {
        return orderDetailsInquiryInfo;
    }
}
