package team.sipe.commerce.order.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.sipe.commerce.order.order.api.dto.OrderCreateHttpRequest;
import team.sipe.commerce.order.order.api.dto.OrderCreateHttpResponse;
import team.sipe.commerce.order.order.application.OrderCreateUseCase;

import java.net.URI;

@RequestMapping("/orders")
@RestController
public class OrderApi {

    private final OrderCreateUseCase orderCreateUseCase;

    public OrderApi(final OrderCreateUseCase orderCreateUseCase) {
        this.orderCreateUseCase = orderCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderCreateHttpResponse> createOrder(@RequestBody final OrderCreateHttpRequest request) {
        final Long orderId = orderCreateUseCase.create(request.toCommand());
        return ResponseEntity.created(URI.create("/orders/" + orderId)).body(new OrderCreateHttpResponse(orderId));
    }
}
