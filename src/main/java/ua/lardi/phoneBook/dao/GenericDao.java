package ua.lardi.phoneBook.dao;

import java.util.List;

public interface GenericDao<T> {

    void save(T object);

    T findById(long id);

    void update(T object);

    void delete(long id);

    List<T> findAll();
}
