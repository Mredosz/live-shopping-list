package pl.mateusz.redosz.userservice.dtos;

import lombok.Builder;

@Builder
public record UserLoginDto(String username,
                           String password) {
}
