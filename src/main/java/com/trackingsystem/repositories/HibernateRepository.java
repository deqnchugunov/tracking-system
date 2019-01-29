package com.trackingsystem.repositories;

import com.trackingsystem.entities.Project;
import com.trackingsystem.entities.User;
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

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(getEntityClass());

//        criteriaQuery.from(getEntityClass());

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
        List entities = query.list();

        transaction.commit();
        session.close();

        return entities;
    }

    public List<Project> getProjectsByUser(String user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);

//        Root<Project> root = criteriaQuery.from(Project.class);
//        criteriaQuery.select(root); // criteriaQuery.from(Project.class);

//        Query query = session.createQuery(criteriaQuery);
//        List<Project> entities = query.getResultList();

//        String hql = "select id, name from projects where id = :idProject";

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
        List entities = query.list();

//        List<Project> projects = criteriaQuery("select id, name from Project where id = :idProject").setParameter("idProject", 1).list();

//        List<Project> result = session.createQuery(hql).setParameter("idProject", 1).list();

//        Root<Project> project = criteriaQuery.from(Project.class);
//
//        Predicate cond = builder.gt(project.get("id"), 1);
//
//        criteriaQuery.where(cond);
//
//        TypedQuery<Project> q = session.createQuery(criteriaQuery);

//        Root<Project> project = cq.from(Project.class);
//        Join<Project, Team> team = project.join(Project_.teams);
//        Join<Team, League> league = team.join(Team_.league);

//        CriteriaQuery<Project> personCriteria = builder.createQuery(Project.class);
//        Root<Project> personRoot = personCriteria.from(Project.class);
//        Join<Project,Order> orders = personRoot.join( Person_.orders );
//        Join<Order,LineItem> orderLines = orders.join( Order_.lineItems );

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
