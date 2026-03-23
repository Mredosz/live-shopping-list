package pl.mateusz.redosz.userservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mateusz.redosz.userservice.dtos.UserRegisterDto;
import pl.mateusz.redosz.userservice.exceptions.UsernameTakenException;
import pl.mateusz.redosz.userservice.services.RegisterService;

@RequestMapping("/auth/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody UserRegisterDto userRegisterDto) throws UsernameTakenException {
        registerService.register(userRegisterDto);
        return ResponseEntity.ok().build();
    }
}
