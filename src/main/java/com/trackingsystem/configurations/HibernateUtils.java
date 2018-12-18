package com.trackingsystem.configurations;

import com.trackingsystem.entities.Privilege;
import com.trackingsystem.entities.Project;
import com.trackingsystem.entities.Role;
import com.trackingsystem.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    static {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();

        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(User.class);

        configuration.addAnnotatedClass(Privilege.class);
        configuration.addAnnotatedClass(Role.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
