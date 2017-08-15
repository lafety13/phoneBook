package ua.lardi.phoneBook.dao.jacksonDao;

import ua.lardi.phoneBook.model.User;

import java.util.Map;

public class JsonPhoneBookModel {
    private long userCount;
    private long contactCount;
    private Map<String, User> users;

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public long getUserCount() {
        return userCount;
    }

    public JsonPhoneBookModel setUserCount(long userCount) {
        this.userCount = userCount;
        return this;
    }

    public long getContactCount() {
        return contactCount;
    }

    public JsonPhoneBookModel setContactCount(long contactCount) {
        this.contactCount = contactCount;
        return this;
    }
}
