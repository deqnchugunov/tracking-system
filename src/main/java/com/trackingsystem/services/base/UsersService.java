package com.trackingsystem.services.base;

import com.trackingsystem.dto.UserDto;
import com.trackingsystem.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    User getUserByUsername(String username);

    void create(UserDto user);
}
