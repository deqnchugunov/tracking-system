package com.trackingsystem.repositories;

import com.trackingsystem.persistance.entities.Project;
import com.trackingsystem.persistance.entities.User;
import com.trackingsystem.repositories.base.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateRepository<T> implements GenericRepository<T> {

    private final SessionFactory sessionFactory;
    private Class<T> entityClass;

    @Autowired
    public HibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery criteriaQuery = builder.createQuery(getEntityClass());
        criteriaQuery.from(getEntityClass());

        List<T> entities = session.createQuery(criteriaQuery).getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public T getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

//        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());

        T entity = session.get(getEntityClass(), id);

        transaction.commit();
        session.close();

        return entity;
    }

    @Override
    public T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public T delete(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    public List<T> getAllUsersAssignedToProject(int projectId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT u.id, u.username, u.password, u.email, u.enabled, u.tokenExpired\n" +
                        "FROM users as u\n" +
                        "INNER JOIN users_projects as up\n" +
                        "ON u.id = up.user_id\n" +
                        "INNER JOIN projects as p\n" +
                        "ON p.id = up.project_id\n" +
                        "WHERE p.id = :projectId";
        NativeQuery query = session.createSQLQuery(hql);
        query.addEntity(User.class);
        query.setParameter("projectId", projectId);
        List<T> entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }

    public List<T> getProjectsByUser(String user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT p.id, p.name, p.description, p.pattern\n " +
                "FROM projects as p\n" +
                "INNER JOIN users_projects as up\n" +
                "ON p.id = up.project_id\n" +
                "INNER JOIN users as u\n" +
                "ON u.id = up.user_id\n" +
                "WHERE u.username = :userName";
        NativeQuery query = session.createSQLQuery(hql);
        query.addEntity(Project.class);
        query.setParameter("userName", user);;
        List<T> entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }

    private Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
