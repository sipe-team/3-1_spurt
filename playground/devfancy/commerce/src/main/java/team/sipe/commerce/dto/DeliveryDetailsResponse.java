package team.sipe.commerce.dto;

import team.sipe.commerce.domain.Delivery;
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

    @Builder
    public DeliveryDetailsResponse(final String recipientName, final String mobile,
                                   final String address, final String zipCode,
                                   final String storePassword, final String deliveryMemo) {
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
