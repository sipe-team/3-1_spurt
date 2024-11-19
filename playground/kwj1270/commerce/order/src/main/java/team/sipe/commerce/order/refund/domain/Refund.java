package team.sipe.commerce.order.refund.domain;

public class Refund {
    private final Long refundId;
    private final RefundProducts refundProducts;
    private final RefundMethod refundMethod;
    private final RefundStatus refundStatus;

    public Refund(final Long refundId,
                  final RefundProducts refundProducts,
                  final RefundMethod refundMethod,
                  final RefundStatus refundStatus
    ) {
        this.refundId = refundId;
        this.refundProducts = refundProducts;
        this.refundMethod = refundMethod;
        this.refundStatus = refundStatus;
    }
}