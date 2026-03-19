package pl.mateusz.redosz.userservice.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.mateusz.redosz.userservice.dtos.UserLoginDto;
import pl.mateusz.redosz.userservice.exceptions.InvalidCredentialsException;
import pl.mateusz.redosz.userservice.security.jwt.JwtGenerator;
import pl.mateusz.redosz.userservice.security.jwt.JwtManager;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final JwtManager jwtManager;

    public void loginUser(UserLoginDto userDto, HttpServletResponse response) throws InvalidCredentialsException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.username(),
                            userDto.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwtManager.addTokenToCookie(response, jwtGenerator.generateToken());
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException();
        }
    }
}
