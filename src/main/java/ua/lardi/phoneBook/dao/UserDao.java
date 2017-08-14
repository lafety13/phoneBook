package ua.lardi.phoneBook.dao;

import ua.lardi.phoneBook.model.User;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login) throws PersistenceException;
}
