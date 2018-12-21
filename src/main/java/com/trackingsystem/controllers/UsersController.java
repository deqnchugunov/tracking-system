package com.trackingsystem.controllers;

import com.trackingsystem.dto.UserDto;
import com.trackingsystem.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/register";
    }

    @PostMapping("/users/register")
    public String register(@ModelAttribute UserDto userDto) {
        usersService.create(userDto);
        return "users/registered";
    }

    @GetMapping("/users/loggedOut")
    public String logout() {
        return "users/loggedOut";
    }

    @GetMapping("/users/manage")
    public String mange() {
        return "users/manage";
    }

}
