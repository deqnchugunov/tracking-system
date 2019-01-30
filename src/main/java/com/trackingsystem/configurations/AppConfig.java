package com.trackingsystem.configurations;

import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.Role;
import com.trackingsystem.persistance.entities.User;
import com.trackingsystem.repositories.HibernateRepository;
import com.trackingsystem.repositories.base.GenericRepository;
import com.trackingsystem.services.ProjectsServiceImpl;
import com.trackingsystem.services.UsersServiceImpl;
import com.trackingsystem.services.base.ProjectsService;
import com.trackingsystem.services.base.UsersService;
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
    GenericRepository<Role> roleGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Role> repository = new HibernateRepository<>(sessionFactory);
        repository.setEntityClass(Role.class);
        return repository;
    }

    @Bean
    @Autowired
    ProjectsService projectsService(GenericRepository<Project> projectRepository) {
        return new ProjectsServiceImpl(projectRepository);
    }

    @Bean
    @Autowired
    UsersService usersService(GenericRepository<User> usersRepository,
                              GenericRepository<Role> rolesRepository,
                              PasswordEncoder passwordEncoder) {
        return new UsersServiceImpl(usersRepository, rolesRepository, passwordEncoder);
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }
}
