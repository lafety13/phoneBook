package ua.lardi.phoneBook.model.builders;

import ua.lardi.phoneBook.model.User;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setId(long id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setLogin(String login) {
        user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setName(String name) {
        user.setName(name);
        return this;
    }

    public User build() {
        return user;
    }
}
