package pl.mateusz.redosz.userservice.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mateusz.redosz.userservice.dtos.UserRegisterDto;
import pl.mateusz.redosz.userservice.entities.UserEntity;
import pl.mateusz.redosz.userservice.enums.UserRole;

public class UserMapper {
    private UserMapper() {
    }

    public static UserEntity toEntity(UserRegisterDto userRegisterDto, PasswordEncoder passwordEncoder) {
        return UserEntity.builder()
                .username(userRegisterDto.username())
                .password(passwordEncoder.encode(userRegisterDto.password()))
                .userRole(UserRole.ROLE_USER)
                .build();
    }
}
