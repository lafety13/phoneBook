package ua.lardi.phoneBook.dao.jdbcDao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ContactRowMapper implements RowMapper<Contact> {
    @Autowired
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
        contact.setUser(userDao.findById(resultSet.getLong("user_id")));

        return contact;
    }
}
