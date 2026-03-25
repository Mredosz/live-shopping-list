package com.pjatk.gateway.security.jwt;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
        setServerAuthenticationConverter(exchange -> {
            String token = Objects.requireNonNull(exchange.getRequest()
                            .getCookies()
                            .getFirst("JWT_token")).getValue();

            if (token.startsWith("Bearer ")) token = token.substring(7);

            return Mono.just(new UsernamePasswordAuthenticationToken(token, token));
        });
    }
}