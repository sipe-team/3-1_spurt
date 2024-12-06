package team.sipe.commerce.order.aggregation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.order.aggregation.dao.OrderQueryDao;
import team.sipe.commerce.order.aggregation.dao.dto.OrderSimpleInformation;

@RequestMapping("/query/orders")
@RestController
public class OrderQueryApi {

    private final OrderQueryDao orderQueryDao;

    public OrderQueryApi(final OrderQueryDao orderQueryDao) {
        this.orderQueryDao = orderQueryDao;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderSimpleInformation> queryOrder(@PathVariable("orderId") final long orderId) {
        return ResponseEntity.ok().body(orderQueryDao.findOrderById(orderId));
    }
}
