package pl.mateusz.redosz.userservice.security.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mateusz.redosz.userservice.config.properties.JwtProperties;

@Component
@RequiredArgsConstructor
public class JwtManager {

    private final JwtProperties jwtProperties;

    public void addTokenToCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(jwtProperties.getTokenName(), token);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtProperties.getTokenCookieExpiration());
        response.addCookie(cookie);
    }
}
