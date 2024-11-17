package com.order.perf.domain;

import com.order.perf.common.BaseTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "REFUNDS")
@Entity
public class Refund extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refundMethodName; // 환불 수단
    private int refundAmount; // 총 환불 금액
    @Enumerated(EnumType.STRING)
    private RefundStatus refundStatus; // 환불 상태

    protected Refund() {
    }

    public Refund(final String refundMethodName, final int refundAmount, final RefundStatus refundStatus) {
        this.refundMethodName = refundMethodName;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
    }

    public Long getId() {
        return id;
    }

    public String getRefundMethodName() {
        return refundMethodName;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }
}
