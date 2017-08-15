package ua.lardi.phoneBook.dao.jacksonDao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("default")
public class UserJacksonDao extends AbstractJacksonDao implements UserDao {

    @Override
    public User findByLogin(String login) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        if (data.getUsers() != null) {
            return data.getUsers().get(login);
        } else {
            return null;
        }
    }

    @Override
    public User save(User object) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        long userId = data.getUserCount() + 1;
        object.setId(userId);
        object.setContacts(new ArrayList<>());
        data.setUserCount(userId);
        if (data.getUsers() == null) {
            data.setUsers(new HashMap<>());
        }
        data.getUsers().put(object.getLogin(), object);
        writeData(data);
        return object;
    }

    @Override
    public User findById(long id) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        for (Map.Entry<String, User> entry : data.getUsers().entrySet()) {
            if (entry.getValue().getId() == id) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void update(User object) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        for (User user : data.getUsers().values()) {
            if (user.getId() == object.getId()) {
                data.getUsers().remove(user.getLogin());
                break;
            }
        }
        data.getUsers().put(object.getLogin(), object);
        writeData(data);
    }

    @Override
    public void delete(long id) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        User user = findById(id);
        data.getUsers().remove(user.getLogin());
        writeData(data);
    }

    @Override
    public List<User> findAll() throws PersistenceException {
        return new ArrayList<>(readData().getUsers().values());
    }
}
