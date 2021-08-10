package com.example.test.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.AdaptCachedBodyGlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Log4j2
@Component
public class PostLogFilter implements GlobalFilter, Ordered {

    public static final int ORDER = new AdaptCachedBodyGlobalFilter().getOrder() + 1;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("after AdaptCachedBodyGlobalFilter");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return ORDER;
    }
}
