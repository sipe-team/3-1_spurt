package com.order.perf.dto;


import com.order.perf.domain.RefundStatus;
import lombok.Getter;

// 환불 정보 DTO
@Getter
public class RefundDetailsDto {
    private String refundMethodName;
    private int refundAmount;
    private RefundStatus refundStatus;
}
