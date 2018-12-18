package com.trackingsystem.controllers;

import com.trackingsystem.entities.User;
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
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/users/register")
    public String register(@ModelAttribute User user) {
        usersService.create(user);
        return "users/registered";
    }

    @GetMapping("/users/loggedOut")
    public String logout() {
        return "users/loggedOut";
    }
}
