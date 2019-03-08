package com.trackingsystem.repositories;

import com.trackingsystem.persistance.entities.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProjectRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Project> getProjectsAssignedToUser(String username) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT p.id, p.name, p.description, p.pattern\n " +
                "FROM projects as p\n" +
                "INNER JOIN users_projects as up\n" +
                "ON p.id = up.project_id\n" +
                "INNER JOIN users as u\n" +
                "ON u.id = up.user_id\n" +
                "WHERE u.username = :username";
        NativeQuery query = session.createSQLQuery(hql);
        query.addEntity(Project.class);
        query.setParameter("username", username);;
        List<Project> entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }
}
