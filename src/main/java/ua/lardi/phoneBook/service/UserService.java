package ua.lardi.phoneBook.service;

import ua.lardi.phoneBook.model.User;

public interface UserService extends GenericService<User> {
    User findUserByLogin(String login);
}
