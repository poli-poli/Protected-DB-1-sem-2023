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
     * Конструктор класса BaseMethod.
     *
     * @param session      Сессия Hibernate, используемая для операций с базой данных.
     * @param entityClass  Тип класса сущности, с которой будет работать этот BaseMethod.
     */
    public BaseMethod(Session session, Class<T> entityClass) {
        this.session = session;
        this.entityClass = entityClass;
    }

    /**
     * Получить сущность по её уникальному идентификатору (первичному ключу).
     *
     * @param id  Первичный ключ сущности, которую нужно получить.
     * @return    Сущность с указанным первичным ключом или null, если не найдено.
     */
    public T get(int id) {
        return session.get(entityClass, id);
    }

    /**
     * Найти сущности на основе конкретного поля и его соответствующего значения.
     *
     * @param field  Название поля, по которому нужно выполнить фильтрацию.
     * @param value  Значение, которое должно соответствовать в указанном поле.
     * @return       Список сущностей, которые соответствуют заданным критериям поля и значения.
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
     * Получить все сущности указанного типа.
     *
     * @return  Список всех сущностей указанного типа.
     */
    public List<T> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        criteria.from(entityClass);
        Query<T> query = session.createQuery(criteria);
        return query.getResultList();
    }

    /**
     * Сохранить или обновить сущность в базе данных.
     *
     * @param entity  Сущность, которую нужно сохранить или обновить.
     */
    public void save(T entity) {
        session.beginTransaction();
        session.saveOrUpdate(entity);
        session.getTransaction().commit();
    }

    /**
     * Удалить сущность из базы данных.
     *
     * @param entity  Сущность, которую нужно удалить.
     */
    public void delete(T entity) {
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }
}