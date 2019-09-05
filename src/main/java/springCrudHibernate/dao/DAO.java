package springCrudHibernate.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface DAO<T> {
    void add(T entity);

    void deleteById(Long id);

    void deleteAll();

    void update(T entity);

    T getUniqueByParam(Object param, String fieldName);

    List<T> getListByParam(Object param, String fieldName);

    List<T> getAll();
}
