package ua.lardi.phoneBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public void save(User user) throws PersistenceException {
        if (user.isNew()) {
            user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
            userDao.save(user);
        } else {
            userDao.update(user);
        }
    }

    @Override
    public void update(User user) throws PersistenceException {
        userDao.update(user);
    }

    @Override
    public User findById(long id) throws PersistenceException {
        return userDao.findById(id);
    }

    @Override
    public User findUserByLogin(String login) throws PersistenceException {
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> findAll() throws PersistenceException {
        return userDao.findAll();
    }
    @Override
    public void delete(long id) throws PersistenceException {
        userDao.delete(id);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setShaPasswordEncoder(ShaPasswordEncoder shaPasswordEncoder) {
        this.shaPasswordEncoder = shaPasswordEncoder;
    }
}
