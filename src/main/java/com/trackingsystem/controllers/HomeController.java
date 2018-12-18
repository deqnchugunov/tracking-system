package com.trackingsystem.controllers;

import com.trackingsystem.entities.Privilege;
import com.trackingsystem.entities.Role;
import com.trackingsystem.entities.User;
import com.trackingsystem.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class HomeController {


    private UsersService usersService;

    @Autowired
    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model) {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = "Guest";
        }

        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/doit")
    public String doIt(Model model) {
        User user = new User();
        Role role = new Role();
        role.setName("ROLE_USER");
        Privilege privilege = new Privilege();
        privilege.setName("WRITE_PRIVILEGE");
        role.setPrivileges(Arrays.asList(privilege));

        user.setUsername("3");
        user.setPassword(new BCryptPasswordEncoder().encode("3"));
        user.setEmail("3@3.com");
        user.setRoles(Arrays.asList(role));

        usersService.create(user);

        return "index";
    }
}


