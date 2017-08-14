package ua.lardi.phoneBook.dao.jdbcDao.mapper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ContactRowMapper implements RowMapper<Contact> {
    private static final Logger LOGGER = LogManager.getLogger(ContactRowMapper.class);
    private UserDao userDao;

    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirstName(resultSet.getString("firstname"));
        contact.setLastName(resultSet.getString("lastname"));
        contact.setMiddleName(resultSet.getString("middlename"));
        contact.setMobilePhone(resultSet.getString("mobilephone"));
        contact.setHomePhone(resultSet.getString("homephone"));
        contact.setAddress(resultSet.getString("address"));
        contact.setEmail(resultSet.getString("email"));
        try {
            contact.setUser(userDao.findById(resultSet.getLong("user_id")));
        } catch (PersistenceException e) {
            LOGGER.error(e);
        }

        return contact;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
