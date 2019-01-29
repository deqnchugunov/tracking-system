package com.trackingsystem.services.base;

import com.trackingsystem.dto.UserDto;
import com.trackingsystem.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void create(UserDto user);
}
