package com.trackingsystem.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateRepository<T> implements BaseGenericRepository<T>{

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

        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());

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

    private Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
