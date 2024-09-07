package com.main.janBnb.service;

import com.main.janBnb.payload.UserDto;
import com.main.janBnb.payload.loginDto;

public interface UserService {
    UserDto signUpUser(UserDto dto);

    String loginUser(loginDto dto);
}
