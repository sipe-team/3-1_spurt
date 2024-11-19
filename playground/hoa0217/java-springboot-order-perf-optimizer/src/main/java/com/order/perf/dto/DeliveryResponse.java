package com.order.perf.dto;

public record DeliveryResponse(
    String recipientName,
    String mobile,
    String address,
    String zipCode,
    String storePassword,
    String deliveryMemo
) {

}
