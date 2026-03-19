package pl.mateusz.redosz.userservice.config;

import io.jsonwebtoken.Jwts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mateusz.redosz.userservice.config.properties.JwtProperties;

@Configuration
public class JwtConfig {

    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties(Jwts.SIG.HS256.key().build());
    }
}
