package com.order.perf.application.dto;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.OrderProduct;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import com.order.perf.dto.DeliveryDetailsResponse;
import com.order.perf.dto.ProductBundleDetailsResponse;
import com.order.perf.dto.ProductDetailsResponse;
import com.order.perf.dto.RefundDetailsDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class OrderDetailsResponse {

    private final List<OrderItemDto> orderItems;

    public OrderDetailsResponse(final List<OrderProduct> orderProducts,
                                final Delivery delivery,
                                final Refund refund) {
        this.orderItems = orderProducts.stream()
                .map(orderProduct -> OrderItemDto.from(orderProduct.getProduct(), delivery, refund))
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
            ProductBundleDetailsResponse bundleDetails = new ProductBundleDetailsResponse(product.getBundleName(), product.getBundleQuantity());
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