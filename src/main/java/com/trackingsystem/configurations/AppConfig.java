package com.trackingsystem.configurations;

import com.trackingsystem.entities.Project;
import com.trackingsystem.repositories.BaseGenericRepository;
import com.trackingsystem.repositories.HibernateRepository;
import com.trackingsystem.services.ProjectsServiceImpl;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Autowired
    BaseGenericRepository<Project> projectGenericRepository(SessionFactory sessionFactory) {
        HibernateRepository<Project> repository = new HibernateRepository<>(sessionFactory);
        repository.setEntityClass(Project.class);
        return repository;
    }

    @Bean
    ProjectsServiceImpl projectsService(BaseGenericRepository<Project> projectRepository) {
        return new ProjectsServiceImpl(projectRepository);
    }

    @Bean
    SessionFactory provideSessionFactory() {
        return HibernateUtils.getSessionFactory();
    }
}
