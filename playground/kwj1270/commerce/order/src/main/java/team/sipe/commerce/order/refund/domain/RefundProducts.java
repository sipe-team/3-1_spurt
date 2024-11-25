package team.sipe.commerce.order.refund.domain;

import java.util.List;

public class RefundProducts {

   private final List<RefundProduct> refundProducts;

    public RefundProducts(final List<RefundProduct> refundProducts) {
        this.refundProducts = refundProducts;
    }
}
