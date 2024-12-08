package team.sipe.commerce.order.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import team.sipe.commerce.delivery.Delivery;
import team.sipe.commerce.product.Product;
import team.sipe.commerce.refund.Refund;
import team.sipe.commerce.delivery.DeliveryDetailsResponse;
import team.sipe.commerce.product.ProductBundleDetailsResponse;
import team.sipe.commerce.product.ProductDetailsResponse;
import team.sipe.commerce.refund.RefundDetailsResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderDetailsResponse {

    private final List<OrderItemDto> orderItems;

    @JsonCreator
    public OrderDetailsResponse(@JsonProperty("orderItems") final List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

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
        private final RefundDetailsResponse refundDetails;

        public static OrderItemDto from(final Product product,
                                        final Delivery delivery,
                                        final Refund refund) {
            ProductBundleDetailsResponse bundleDetails = new ProductBundleDetailsResponse("Bundle Name", 10); // 예제 값 설정
            ProductDetailsResponse productDetails = ProductDetailsResponse.from(product);
            DeliveryDetailsResponse deliveryDetails = DeliveryDetailsResponse.from(delivery);
            RefundDetailsResponse refundDetails = RefundDetailsResponse.from(refund);

            return new OrderItemDto(bundleDetails, productDetails, deliveryDetails, refundDetails);
        }

        @JsonCreator
        private OrderItemDto(@JsonProperty("productBundleDetails") final ProductBundleDetailsResponse productBundleDetails,
                             @JsonProperty("productDetails") final ProductDetailsResponse productDetails,
                             @JsonProperty("deliveryDetails") final DeliveryDetailsResponse deliveryDetails,
                             @JsonProperty("refundDetails") final RefundDetailsResponse refundDetails) {
            this.productBundleDetails = productBundleDetails;
            this.productDetails = productDetails;
            this.deliveryDetails = deliveryDetails;
            this.refundDetails = refundDetails;
        }
    }
}