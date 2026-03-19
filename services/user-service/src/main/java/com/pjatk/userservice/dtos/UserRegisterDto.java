package com.pjatk.userservice.dtos;

import lombok.Builder;

@Builder
public record UserRegisterDto(String username,
                              String password,
                              String confirmPassword) {
}
