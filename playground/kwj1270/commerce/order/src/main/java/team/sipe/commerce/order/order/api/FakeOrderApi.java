package team.sipe.commerce.order.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
public class FakeOrderApi {

    @GetMapping("/{userId}")
    public ResponseEntity<OrderInfoHttpRequest> orderInfo(@PathVariable final long userId) {
        return ResponseEntity.ok(new OrderInfoHttpRequest(userId));
    }

    record OrderInfoHttpRequest(
            long orderId,
            long userId,
            OrderStatus status
    ) {

        public OrderInfoHttpRequest(long userId) {
            this(1, userId, OrderStatus.PENDING);
        }
    }

    enum OrderStatus {
        PENDING, PROCESSING, FINISHED, CANCELED
    }
}
