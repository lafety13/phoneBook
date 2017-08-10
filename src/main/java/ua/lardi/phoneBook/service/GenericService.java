package ua.lardi.phoneBook.service;

public interface GenericService<T> {
    T save(T object);

    T findById(long id);

    void delete(long id);
}
