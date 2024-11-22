package com.order.perf.dto;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.OrderStatus;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import lombok.Getter;

import java.time.LocalDateTime;

// 주문 항목 DTO
@Getter
public class OrderItemDto {

    private final ProductBundleDetailsResponse productBundleDetails;
    private final ProductDetailsResponse productDetails;
    private final DeliveryDetailsResponse deliveryDetails;
    private final RefundDetailsDto refundDetails;

    public static OrderItemDto from(final Product product,
                                    final Delivery delivery,
                                    final Refund refund) {
        ProductBundleDetailsResponse bundleDetails = new ProductBundleDetailsResponse("Bundle Name", 10); // 예제 값 설정
        ProductDetailsResponse productDetails = ProductDetailsResponse.from(product);
        DeliveryDetailsResponse deliveryDetails = DeliveryDetailsResponse.from(delivery);
        RefundDetailsDto refundDetails = RefundDetailsDto.from(refund);

        return new OrderItemDto(bundleDetails, productDetails, deliveryDetails, refundDetails);
    }

    private OrderItemDto(final ProductBundleDetailsResponse productBundleDetails,
                         final ProductDetailsResponse productDetails,
                         final DeliveryDetailsResponse deliveryDetails,
                         final RefundDetailsDto refundDetails) {
        this.productBundleDetails = productBundleDetails;
        this.productDetails = productDetails;
        this.deliveryDetails = deliveryDetails;
        this.refundDetails = refundDetails;
    }
}
