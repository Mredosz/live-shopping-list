package pl.mateusz.redosz.userservice.security.jwt;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.mateusz.redosz.userservice.entities.UserEntity;

@Component
@RequiredArgsConstructor
public class JwtService {
    private final JwtManager jwtManager;
    private final JwtGenerator jwtGenerator;

    public void refreshUserToken(UserEntity user, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getUsername(),
                        null, user.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwtManager.addTokenToCookie(response, jwtGenerator.generateToken());
    }
}
