package com.order.perf.dto;

import com.order.perf.domain.RefundStatus;

public record RefundResponse(
    String refundMethodName,
    int refundAmount,
    RefundStatus refundStatus
) {

}
