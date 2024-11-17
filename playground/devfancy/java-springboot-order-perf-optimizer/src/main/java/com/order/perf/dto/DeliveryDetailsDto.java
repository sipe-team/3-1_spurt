package com.order.perf.dto;

import lombok.Getter;

// 배송 정보 DTO
@Getter
public class DeliveryDetailsDto {

    private String recipientName;
    private String mobile;
    private String address;
    private String zipCode;
    private String storePassword;
    private String deliveryMemo;
}
