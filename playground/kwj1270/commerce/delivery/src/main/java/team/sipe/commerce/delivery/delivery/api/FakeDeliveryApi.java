package team.sipe.commerce.delivery.delivery.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.delivery.delivery.domain.DeliveryStatus;

@RequestMapping("/delivery")
@RestController
public class FakeDeliveryApi {

    @GetMapping("/{orderId}")
    public ResponseEntity<DeliveryInfoHttpResponse> deliveryInfo(@PathVariable final long orderId) {
        return ResponseEntity.ok(new DeliveryInfoHttpResponse(orderId));
    }

    record DeliveryInfoHttpResponse(
            long deliveryId,
            long orderId,
            DeliveryStatus status
    ) {
        public DeliveryInfoHttpResponse(long orderId) {
            this(1, orderId, DeliveryStatus.DELIVERED);
        }
    }
}
