package ua.lardi.phoneBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public void save(User user) {
        if (user.isNew()) {
            user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
            userDao.save(user);
        } else {
            userDao.update(user);
        }
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

}
