package pl.mateusz.redosz.userservice.dtos;

import lombok.Builder;

@Builder
public record UserRegisterDto(String username,
                              String password,
                              String confirmPassword) {
}
