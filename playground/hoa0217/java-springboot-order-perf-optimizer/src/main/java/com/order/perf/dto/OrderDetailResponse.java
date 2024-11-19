package com.order.perf.dto;

import java.util.List;

public record OrderDetailResponse(
    OrderResponse order,
    List<ProductResponse> products,
    DeliveryResponse delivery,
    RefundResponse refund
) {

}
