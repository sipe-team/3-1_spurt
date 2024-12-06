package com.order.perf.filter;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class LatencyMetricFilter implements WebFilter {

    private final MeterRegistry meterRegistry;

    public LatencyMetricFilter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        Timer.Sample sample = Timer.start(meterRegistry);
        return chain.filter(exchange).doFinally(signalType -> {
            sample.stop(Timer.builder("http.server.requests.latency")
                    .tag("uri", path)
                    .register(meterRegistry));
        });
    }
}
