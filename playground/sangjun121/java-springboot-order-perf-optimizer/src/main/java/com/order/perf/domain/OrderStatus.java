package com.order.perf.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    CANCELED, // 주문이 취소된 상태
    PENDING,        // 주문이 대기 중인 상태 (결제 대기 등)
    CONFIRMED,      // 주문이 확인된 상태
    PROCESSING,     // 주문이 처리 중인 상태
    SHIPPED,        // 주문이 발송된 상태
    DELIVERED,      // 주문이 배송 완료된 상태
    RETURNED,       // 주문이 반품된 상태
    COMPLETED       // 주문이 최종 완료된 상태
}
