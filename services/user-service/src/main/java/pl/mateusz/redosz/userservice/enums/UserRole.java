package pl.mateusz.redosz.userservice.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_USER("USER");

    private final String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

}
