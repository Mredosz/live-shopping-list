package com.pjatk.gateway.security.jwt;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);

        setRequiresAuthenticationMatcher((ServerWebExchange exchange) -> {
            if (exchange.getRequest().getURI().getPath().startsWith("/auth")) {
                return ServerWebExchangeMatcher.MatchResult.notMatch();
            }
            return ServerWebExchangeMatcher.MatchResult.match();
        });

        setServerAuthenticationConverter(exchange -> {
            var cookie = exchange.getRequest()
                    .getCookies()
                    .getFirst("JWT_token");

            if (cookie == null) {
                return Mono.empty();
            }

            String token = cookie.getValue();
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            return Mono.just(new UsernamePasswordAuthenticationToken(token, token));
        });
    }
}