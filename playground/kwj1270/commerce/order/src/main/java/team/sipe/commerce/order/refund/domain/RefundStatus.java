package team.sipe.commerce.order.refund.domain;

public enum RefundStatus {
    REQUESTED,
    APPROVED,
    REJECTED,
    // 최종 상태
    PARTIAL_COMPLETED,
    COMPLETED
}

