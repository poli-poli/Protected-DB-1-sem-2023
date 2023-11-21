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

    /**
     * ����������� ������ BaseMethod.
     *
     * @param session      ������ Hibernate, ������������ ��� �������� � ����� ������.
     * @param entityClass  ��� ������ ��������, � ������� ����� �������� ���� BaseMethod.
     */
    public BaseMethod(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    /**
     * �������� �������� �� � ����������� �������������� (���������� �����).
     *
     * @param id  ��������� ���� ��������, ������� ����� ��������.
     * @return    �������� � ��������� ��������� ������ ��� null, ���� �� �������.
     */
    public T get(int id) {
        return session.get(entityClass, id);
    }

    /**
     * ����� �������� �� ������ ����������� ���� � ��� ���������������� ��������.
     *
     * @param field  �������� ����, �� �������� ����� ��������� ����������.
     * @param value  ��������, ������� ������ ��������������� � ��������� ����.
     * @return       ������ ���������, ������� ������������� �������� ��������� ���� � ��������.
     */
    public List<T> find(String field, Object value) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root).where(builder.equal(root.get(field), value));
        Query<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * �������� ��� �������� ���������� ����.
     *
     * @return  ������ ���� ��������� ���������� ����.
     */
    public List<T> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        criteria.from(entityClass);
        Query<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * ��������� ��� �������� �������� � ���� ������.
     *
     * @param entity  ��������, ������� ����� ��������� ��� ��������.
     */
    public void save(T entity) {
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    /**
     * ������� �������� �� ���� ������.
     *
     * @param entity  ��������, ������� ����� �������.
     */
    public void delete(T entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }
}