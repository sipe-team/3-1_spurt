package team.sipe.commerce.order.refund.domain;

public class Refund {

    private final Long id;
    private final RefundProducts refundProducts;
    private final RefundMethod refundMethod;
    private final RefundStatus refundStatus;

    public Refund(final Long id,
                  final RefundProducts refundProducts,
                  final RefundMethod refundMethod,
                  final RefundStatus refundStatus
    ) {
        this.id = id;
        this.refundProducts = refundProducts;
        this.refundMethod = refundMethod;
        this.refundStatus = refundStatus;
    }
}