package team.sipe.commerce.delivery;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

// 배송 정보 DTO
@Getter
public class DeliveryDetailsResponse {

    private final String recipientName;
    private final String mobile;
    private final String address;
    private final String zipCode;
    private final String storePassword;
    private final String deliveryMemo;

    @JsonCreator
    @Builder
    public DeliveryDetailsResponse(@JsonProperty("recipientName") final String recipientName,
                                   @JsonProperty("mobile") final String mobile,
                                   @JsonProperty("address") final String address,
                                   @JsonProperty("zipCode") final String zipCode,
                                   @JsonProperty("storePassword") final String storePassword,
                                   @JsonProperty("deliveryMemo") final String deliveryMemo) {
        this.recipientName = recipientName;
        this.mobile = mobile;
        this.address = address;
        this.zipCode = zipCode;
        this.storePassword = storePassword;
        this.deliveryMemo = deliveryMemo;
    }

    public static DeliveryDetailsResponse from(final Delivery delivery) {
        return DeliveryDetailsResponse.builder()
                .recipientName(delivery.getRecipientName())
                .mobile(delivery.getMobile())
                .address(delivery.getAddress())
                .zipCode(delivery.getZipCode())
                .storePassword(delivery.getStorePassword())
                .deliveryMemo(delivery.getDeliveryMemo())
                .build();
    }
}
