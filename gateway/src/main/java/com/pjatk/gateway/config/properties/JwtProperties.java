package com.pjatk.gateway.config.properties;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String key;

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }
}
