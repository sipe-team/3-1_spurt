package com.order.perf.dto;

import lombok.Getter;

// 배송 정보 DTO
@Getter
public class DeliveryDetailsDto {

    private final String recipientName;
    private final String mobile;
    private final String address;
    private final String zipCode;
    private final String storePassword;
    private final String deliveryMemo;

    public DeliveryDetailsDto(final String recipientName, final String mobile,
                              final String address, final String zipCode,
                              final String storePassword, final String deliveryMemo) {
        this.recipientName = recipientName;
        this.mobile = mobile;
        this.address = address;
        this.zipCode = zipCode;
        this.storePassword = storePassword;
        this.deliveryMemo = deliveryMemo;
    }
}
