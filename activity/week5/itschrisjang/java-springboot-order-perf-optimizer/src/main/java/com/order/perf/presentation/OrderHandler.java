package com.order.perf.presentation;

import com.order.perf.application.OrderService;
import com.order.perf.application.dto.OrderDetailsResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    private final OrderService orderService;

    public OrderHandler(final OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> findByOrderDetail(ServerRequest request) {
        return orderService.findOrderDetailsWithCache(Long.valueOf(request.pathVariable("id")))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
