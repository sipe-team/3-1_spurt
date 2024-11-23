package team.sipe.commerce.domain;

import team.sipe.commerce.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "REFUNDS")
@Entity
public class Refund extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "refund_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "refund_method_name", nullable = false)
    private RefundMethodName refundMethodName; // 환불 수단

    @Column(name = "refund_amount", nullable = false)
    private int refundAmount; // 총 환불 금액

    @Enumerated(EnumType.STRING)
    @Column(name = "refund_status", nullable = false)
    private RefundStatus refundStatus; // 환불 상태

    protected Refund() {
    }

    public Refund(final RefundMethodName refundMethodName, final int refundAmount, final RefundStatus refundStatus) {
        this.refundMethodName = refundMethodName;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
    }
}
