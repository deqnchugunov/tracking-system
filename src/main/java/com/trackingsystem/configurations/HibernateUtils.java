package com.trackingsystem.configurations;

import com.trackingsystem.persistance.entities.Privilege;
import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.Role;
import com.trackingsystem.persistance.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();

        configuration.addAnnotatedClass(Privilege.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
