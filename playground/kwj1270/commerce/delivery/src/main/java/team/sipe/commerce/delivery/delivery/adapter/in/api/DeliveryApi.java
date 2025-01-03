package team.sipe.commerce.delivery.delivery.adapter.in.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.delivery.delivery.domain.DeliveryStatus;

@RequestMapping("/query")
@RestController
public class DeliveryApi {

    @GetMapping("/delivery/orders/{orderId}")
    public ResponseEntity<DeliveryInfoHttpResponse> deliveryInfo(@PathVariable("orderId") final long orderId) throws InterruptedException {
        return ResponseEntity.ok(new DeliveryInfoHttpResponse(orderId));
    }

    record DeliveryInfoHttpResponse(
            long deliveryId,
            long orderId,
            DeliveryStatus deliveryStatus
    ) {
        public DeliveryInfoHttpResponse(long orderId) {
            this(1, orderId, DeliveryStatus.DELIVERING);
        }
    }
}