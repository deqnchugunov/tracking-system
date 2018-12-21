package com.trackingsystem.controllers;

import com.trackingsystem.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class HomeController {

    private UsersService usersService;

    @Autowired
    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        String username;
        if (principal != null) {
            username = principal.getName();
            Collection<? extends GrantedAuthority> authorities = usersService.loadUserByUsername(username).getAuthorities();
            model.addAttribute("authorities", authorities);
//            UsersServiceImpl usersServiceImpl = new UsersServiceImpl();
//            authorities.addAll(usersServiceImpl.getAuthorities(usersService.loadUserByUsername(username).re))
        } else {
            username = "Guest";
        }

        model.addAttribute("username", username);
        return "index";
    }

//    @GetMapping("/doit")
//    public String doIt(Model model) {
//        UserDto user = new UserDto();
//        Role role = new Role();
//        role.setName("ROLE_USER");
//        Privilege privilege = new Privilege();
//        privilege.setName("WRITE_PRIVILEGE");
//        role.setPrivileges(Arrays.asList(privilege));
//
//        user.setUsername("3");
//        user.setPassword(new BCryptPasswordEncoder().encode("3"));
//        user.setEmail("3@3.com");
//        user.setRoles(Arrays.asList(role));
//
//        usersService.create(user);
//
//        return "index";
//    }
}


