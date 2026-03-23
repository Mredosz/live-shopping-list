package com.pjatk.gateway.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter implements GlobalFilter {

    private final JwtManager jwtManager;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (request.getURI().getPath().startsWith("/auth")) {
            return chain.filter(exchange);
        }

        HttpCookie cookie = request.getCookies().getFirst("JWT_token");

        if (cookie == null) {
            return unauthorized(exchange);
        }

        String token = cookie.getValue();

        try {
            jwtManager.validateToken(token);
        } catch (Exception e) {
            return unauthorized(exchange);
        }

        String username = jwtManager.getUsernameFromJWT(token);

        ServerHttpRequest mutatedRequest = request.mutate()
                .header("X-User-Id", username)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}