package team.sipe.commerce.order.order.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.sipe.commerce.order.order.api.dto.OrderCreateHttpRequest;
import team.sipe.commerce.order.order.api.dto.OrderCreateHttpResponse;
import team.sipe.commerce.order.order.application.usecase.OrderCreateUseCase;
import team.sipe.commerce.order.order.application.usecase.OrderStatusFinishUseCase;

import java.net.URI;

@RequestMapping("/orders")
@RestController
public class OrderApi {

    private final OrderCreateUseCase orderCreateUseCase;
    private final OrderStatusFinishUseCase orderStatusFinishUseCase;

    public OrderApi(final OrderCreateUseCase orderCreateUseCase, final OrderStatusFinishUseCase orderStatusFinishUseCase) {
        this.orderCreateUseCase = orderCreateUseCase;
        this.orderStatusFinishUseCase = orderStatusFinishUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderCreateHttpResponse> createOrder(@RequestBody final OrderCreateHttpRequest request) {
        final Long orderId = orderCreateUseCase.create(request.toCommand());
        return ResponseEntity.created(URI.create("/orders/" + orderId)).body(new OrderCreateHttpResponse(orderId));
    }

//    @PostMapping("/finished")
//    public ResponseEntity<Long> changeFinishedOrder() {
//        final Long l = orderStatusFinishUseCase.changeFinish(1L, 1L);
//        return ResponseEntity.ok().body(1L);
//    }
}
