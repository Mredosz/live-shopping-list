package com.pjatk.gateway.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtManager jwtManager;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();

        try {
            jwtManager.validateToken(token);
            String username = jwtManager.getUsernameFromJWT(token);
            return Mono.just(new UsernamePasswordAuthenticationToken(username, token, null));
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}