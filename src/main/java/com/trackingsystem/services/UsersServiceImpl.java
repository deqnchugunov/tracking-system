package com.trackingsystem.services;

import com.trackingsystem.dto.UserDto;
import com.trackingsystem.entities.Privilege;
import com.trackingsystem.entities.Project;
import com.trackingsystem.entities.Role;
import com.trackingsystem.entities.User;
import com.trackingsystem.repositories.HibernateRepository;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.base.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private final GenericRepository<User> usersRepository;
    private final GenericRepository<Role> rolesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(GenericRepository<User> usersRepository,
                            GenericRepository<Role> rolesRepository,
                            PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUsername(String username) {
        return usersRepository.getAll()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.getAll();
    }

    @Override
    public void create(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRoles(Arrays.asList(getRoleByName("USER")));
        user.setEnabled(true);
        user.setTokenExpired(false);
        usersRepository.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());
        builder.roles(String.join(", ", convertRolesToListOfStrings(user.getRoles())));
        //builder.authorities(getAuthorities(user.getRoles()));

        return builder.build();
    }

    public List<User> getAllUsersAssignedToProject(int projectId) {
        List<User> users = ((HibernateRepository<User>) usersRepository).getAllUsersAssignedToProject(projectId);
        return users;
    }

//    public List<User> getAllUsersByProjectId(String id) {
//        (HibernateRepository<User>) asd = ((HibernateRepository<User>) usersRepository).getAllUserByProjectId(id);
//                .stream()
//                .filter(u -> u.get).equals(username))
//                .findFirst()
//                .orElse(null);
//
//        return asd;
//    }


    private List<String> convertRolesToListOfStrings(Collection<Role> roles) {
        List<String> userRoles = new ArrayList<>();
        for (Role role : roles) {
            userRoles.add(role.getName());
        }
        return userRoles;
    }

    private Role getRoleByName(String name) {
        return rolesRepository.getAll()
                .stream()
                .filter(r ->r.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
