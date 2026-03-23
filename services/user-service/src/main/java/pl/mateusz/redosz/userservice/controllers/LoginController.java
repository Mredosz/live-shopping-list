package pl.mateusz.redosz.userservice.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.redosz.userservice.dtos.UserLoginDto;
import pl.mateusz.redosz.userservice.exceptions.InvalidCredentialsException;
import pl.mateusz.redosz.userservice.services.LoginService;

@RequestMapping("/auth/login")
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws InvalidCredentialsException {
        loginService.loginUser(userLoginDto, response);
        return ResponseEntity.ok().build();
    }
}
