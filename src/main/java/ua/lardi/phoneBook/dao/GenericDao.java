package ua.lardi.phoneBook.dao;

import java.util.List;

public interface GenericDao<T> {

    T save(T object) throws PersistenceException;

    T findById(long id) throws PersistenceException;;

    void update(T object) throws PersistenceException;;

    void delete(long id) throws PersistenceException;;

    List<T> findAll() throws PersistenceException;
}
