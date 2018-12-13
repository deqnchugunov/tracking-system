package com.trackingsystem.configurations;

import com.trackingsystem.entities.Project;
import com.trackingsystem.entities.User;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.repositories.HibernateRepository;
import com.trackingsystem.services.base.ProjectsService;
import com.trackingsystem.services.base.UsersService;
import com.trackingsystem.services.ProjectsServiceImpl;
import com.trackingsystem.services.UsersServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    @Autowired
    GenericRepository<Project> projectGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Project> repository = new HibernateRepository<>(sessionFactory);
        repository.setEntityClass(Project.class);
        return repository;
    }

    @Bean
    @Autowired
    GenericRepository<User> userGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<User> repository = new HibernateRepository<>(sessionFactory);
        repository.setEntityClass(User.class);
        return repository;
    }

    @Bean
    @Autowired
    ProjectsService projectsService(GenericRepository<Project> projectRepository) {
        return new ProjectsServiceImpl(projectRepository);
    }

    @Bean
    @Autowired
    UsersService usersService(GenericRepository<User> usersRepository, PasswordEncoder passwordEncoder) {
        return new UsersServiceImpl(usersRepository, passwordEncoder);
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }
}
