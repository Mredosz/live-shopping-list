package com.pjatk.userservice.mappers;

import com.pjatk.userservice.dtos.UserRegisterDto;
import com.pjatk.userservice.entities.User;

public class UserMapper {
    public static User toEntity (UserRegisterDto userRegisterDto){
        return User.builder()
    }
}
