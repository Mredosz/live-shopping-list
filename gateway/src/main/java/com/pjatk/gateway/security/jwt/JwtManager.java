package com.pjatk.gateway.security.jwt;

import com.pjatk.gateway.config.properties.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtManager {

    private final JwtProperties jwtProperties;

    public String getUsernameFromJWT(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(jwtProperties.getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public void validateToken(String token) {
        if (token == null || token.isBlank()) {
            throw new AuthenticationCredentialsNotFoundException("JWT is null or empty. code: 401");
        }
        Jwts.parser()
                .verifyWith(jwtProperties.getKey())
                .build()
                .parseSignedClaims(token);
    }
}
