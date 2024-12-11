package team.sipe.commerce.order.presentation;

import team.sipe.commerce.order.application.dto.OrderDetailsResponse;
import team.sipe.commerce.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/api/orders/{id}")
    public ResponseEntity<OrderDetailsResponse> findByOrderDetail(@PathVariable final Long id) {
        OrderDetailsResponse response = orderService.findOrderDetails(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(path = "/api/orders/mongodb/{id}")
    public ResponseEntity<OrderDetailsResponse> findByOrderDetailWithMongoDb(@PathVariable final Long id) {
        OrderDetailsResponse response = orderService.findOrderDetailsWithSnapshot(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}