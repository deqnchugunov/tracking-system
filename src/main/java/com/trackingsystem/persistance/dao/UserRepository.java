package com.trackingsystem.persistance.dao;

import com.trackingsystem.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Override
    void delete(User user);
}

