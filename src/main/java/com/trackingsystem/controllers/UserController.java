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
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/user/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute UserDto userDto) {
        usersService.create(userDto);
        return "users/registered";
    }

    @GetMapping("/user/loggedOut")
    public String logout() {
        return "users/loggedOut";
    }

    @GetMapping("/user/manage")
    public String mange() {
        return "users/manage";
    }

    @GetMapping("/user/account")
    public String account() {
        return "users/account";
    }

    @GetMapping("/user/page")
    public String page() {
        return "users/page";
    }
}
