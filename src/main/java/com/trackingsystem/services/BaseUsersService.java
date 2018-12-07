package com.trackingsystem.services;

import com.trackingsystem.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface BaseUsersService extends UserDetailsService {
    User getUserByUsername(String username);

    void create(User user);
}
