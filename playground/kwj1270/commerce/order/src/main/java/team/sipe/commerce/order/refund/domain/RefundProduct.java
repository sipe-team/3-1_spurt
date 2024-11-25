package team.sipe.commerce.order.refund.domain;

public class RefundProduct {

    private final Long orderId;
    private final Long productId;
    private final int refundCount;
    private final RefundPrice refundPrice;
    private final RefundProductStatus refundProductStatus;

    public RefundProduct(final Long orderId, final int refundCount, final Long productId, final RefundPrice refundPrice, final RefundProductStatus refundProductStatus) {
        this.orderId = orderId;
        this.refundCount = refundCount;
        this.productId = productId;
        this.refundPrice = refundPrice;
        this.refundProductStatus = refundProductStatus;
    }
}
