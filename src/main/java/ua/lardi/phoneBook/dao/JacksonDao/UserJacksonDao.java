package ua.lardi.phoneBook.dao.JacksonDao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Profile("default")
public class UserJacksonDao extends JacksonDaoSupport implements UserDao {

    @Override
    public User findByLogin(String login) {
        JsonPhoneBookModel jsonPhoneBookModel = readData();
        return jsonPhoneBookModel.getUsers().get(login);
    }

    @Override
    public User save(User user) {
        JsonPhoneBookModel jsonPhoneBookModel = readData();
        long userId = jsonPhoneBookModel.getUserCount() + 1;
        user.setId(userId);
        user.setContacts(Collections.emptyList());
        jsonPhoneBookModel.setUserCount(userId);
        if (jsonPhoneBookModel.getUsers() == null) {
            jsonPhoneBookModel.setUsers(Collections.emptyMap());
        }
        jsonPhoneBookModel.getUsers().put(user.getLogin(), user);
        writeData(jsonPhoneBookModel);

        return user;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<User> findAll() {
        JsonPhoneBookModel jsonPhoneBookModel = readData();
        return new ArrayList<>(jsonPhoneBookModel.getUsers().values());
    }
}
