package com.order.perf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Table("refunds")
public class Refund {

    @Id
    @Column("refund_id")
    private Long id;

    @Column("refund_method_name")
    private String refundMethodName; // 환불 수단 (Enum의 이름 저장)

    @Column("refund_amount")
    private int refundAmount; // 총 환불 금액

    @Column("refund_status")
    private String refundStatus; // 환불 상태 (Enum의 이름 저장)

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Refund() {
    }

    public Refund(final RefundMethodName refundMethodName, final int refundAmount, final RefundStatus refundStatus) {
        this.refundMethodName = refundMethodName.name();
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus.name();
    }
}
