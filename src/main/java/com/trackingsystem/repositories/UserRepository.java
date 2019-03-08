package com.trackingsystem.repositories;

import com.trackingsystem.persistance.entities.Role;
import com.trackingsystem.persistance.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<User> getUsersAssignedToProject(String projectName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.id, u.username, u.email, u.password, u.enabled, u.tokenExpired\n" + // , r.name as role\n" +
                "FROM users as u\n" +
                "INNER JOIN users_projects as up\n" +
                "ON u.id = up.user_id\n" +
                "INNER JOIN projects as p\n" +
                "ON p.id = up.project_id\n" +
//                "INNER JOIN users_roles as ur\n" +
//                "ON u.id = ur.user_id\n" +
//                "INNER JOIN roles as r\n" +
//                "ON r.id = ur.role_id\n" +
                "WHERE p.name = :projectName";
        NativeQuery query = session.createSQLQuery(hql);
        query.addEntity(User.class);
//        query.addEntity(Role.class);
        query.setParameter("projectName", projectName);
        List<User> entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }

    public void assignProjectToAdminUsers(int projectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String qry = "";
        List<User> adminUsers = getAdminUsers();
        for(User user: adminUsers) {
            qry += "(";
            qry += user.getId() + ", " + projectId;
            qry += "), ";
        }
        String values = qry.substring(0, qry.length() - 2);

        String hql = "INSERT INTO users_projects (user_id, project_id) VALUES " + values;
//        session.do
        session.createSQLQuery(hql).executeUpdate();

        transaction.commit();
        session.close();
    }

    // Helpers

    private List<User> getAdminUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.id, u.username, u.email, u.password, u.enabled, u.tokenExpired\n" +
                "FROM users as u\n" +
                "INNER JOIN users_roles as ur\n" +
                "ON u.id = ur.user_id\n" +
                "INNER JOIN roles as r\n" +
                "ON r.id = ur.role_id\n" +
                "WHERE r.name = 'ADMIN'";
        NativeQuery query = session.createSQLQuery(hql);
        query.addEntity(User.class);
        List<User> entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }
}
