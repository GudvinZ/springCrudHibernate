package springCrudHibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.InvalidParameterException;
import java.util.List;

public abstract class AbstractHibernateDAO<T> implements DAO<T> {

    private Class modelClass;
    private SessionFactory sessionFactory;

    public Class getModelClass() {
        return modelClass;
    }

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
    }

    @SuppressWarnings("JpaQlInspection")
    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM " + modelClass.getSimpleName() + " WHERE id=?1")
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM " + modelClass.getSimpleName()).executeUpdate();

    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);

    }

    @Override
    public T getUniqueByParam(Object param, String fieldName) {
        Session session = sessionFactory.getCurrentSession();
        return (T) session.createQuery("FROM " + modelClass.getSimpleName() + " where " + fieldName + " = ?1")
                .setParameter(1, param)
                .uniqueResult();

    }

    @Override
    public List<T> getListByParam(Object param, String fieldName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + modelClass.getSimpleName() + " where " + fieldName + " = ?1")
                .setParameter(1, param)
                .list();
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + modelClass.getSimpleName()).list();

    }


}
