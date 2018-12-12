package com.trackingsystem.controllers;

import com.trackingsystem.entities.User;
import com.trackingsystem.services.BaseUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {
    private final BaseUsersService usersService;

    @Autowired
    public UsersController(BaseUsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/users/register")
    public String register(@ModelAttribute User user) {
        usersService.create(user);
        return "users/register";
    }
}
