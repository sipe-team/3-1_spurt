package team.sipe.commerce.display;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.display.application.DeliveryClient;
import team.sipe.commerce.display.application.OrderClient;

import java.util.List;

@RequestMapping("/display")
@RestController
public class DisplayApi {

    private final OrderClient orderClient;
    private final DeliveryClient deliveryClient;

    public DisplayApi(final OrderClient orderClient, final DeliveryClient deliveryClient) {
        this.orderClient = orderClient;
        this.deliveryClient = deliveryClient;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DisplayOrderInfoHttpResponse> displayOrderInfo(@PathVariable final String orderId) {
        return ResponseEntity.ok(new DisplayOrderInfoHttpResponse(List.of()));
    }

    record DisplayOrderInfoHttpResponse(
            List<DisplayOrderItem> orderItems
    ) {
    }

    record DisplayOrderItem(
            DisplayProductBundleDetail productBundleDetail,
            DisplayProductDetail productDetail,
            DisplayDeliveryDetail deliveryDetail,
            DisplayRefundDetail refundDetail
    ) {
    }

    record DisplayProductBundleDetail() {
    }

    record DisplayProductDetail() {
    }

    record DisplayDeliveryDetail() {
    }

    record DisplayRefundDetail() {
    }
}
