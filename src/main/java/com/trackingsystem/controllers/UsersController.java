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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user) {
        usersService.create(user);
        return "register";
    }
}
