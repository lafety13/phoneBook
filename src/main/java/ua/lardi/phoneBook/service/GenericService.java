package ua.lardi.phoneBook.service;

import java.util.List;

public interface GenericService<T> {
    T save(T object);

    T findById(long id);

    void update(T object);

    void delete(long id);

    List<T> findAll();
}
