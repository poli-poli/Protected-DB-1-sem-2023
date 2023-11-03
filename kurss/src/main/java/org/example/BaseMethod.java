package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseMethod<T> {

    private final Session session;
    private final Class<T> entityClass;

    public BaseMethod(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    public T get(int id) {
        return session.get(entityClass, id);
    }

    public List<T> find(String field, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root).where(builder.equal(root.get(field), value));
        Query<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    public List<T> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        criteria.from(entityClass);
        Query<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    public void save(T entity) {
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    public void delete(T entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }
}