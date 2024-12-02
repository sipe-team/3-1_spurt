package team.sipe.spurt.ecommerce.order.domain

enum class RefundStatus {
    PENDING,    // 환불 요청 대기 중
    APPROVED,   // 환불 승인됨
    REJECTED,   // 환불 거부됨
    PROCESSING, // 환불 처리 중
    COMPLETED,  // 환불 완료됨
    FAILED,     // 환불 실패
    ;
}