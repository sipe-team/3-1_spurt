package com.order.perf.application.dto;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import com.order.perf.dto.DeliveryDetailsResponse;
import com.order.perf.dto.ProductBundleDetailsResponse;
import com.order.perf.dto.ProductDetailsResponse;
import com.order.perf.dto.RefundDetailsDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDetailsResponse {

    private final List<OrderItemDto> orderItems;

    public OrderDetailsResponse(final List<Product> products,
                                final Delivery delivery,
                                final Refund refund) {
        this.orderItems = products.stream()
                .map(product -> OrderItemDto.from(product, delivery, refund))
                .collect(Collectors.toList());
    }

    // 후보 3
    @Getter
    public static class OrderItemDto {

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
}