package ua.lardi.phoneBook.service;

import ua.lardi.phoneBook.dao.PersistenceException;

import java.util.List;

public interface GenericService<T> {
    void save(T object) throws PersistenceException;

    T findById(long id) throws PersistenceException;

    void update(T object) throws PersistenceException;

    void delete(long id) throws PersistenceException;

    List<T> findAll() throws PersistenceException;
}
