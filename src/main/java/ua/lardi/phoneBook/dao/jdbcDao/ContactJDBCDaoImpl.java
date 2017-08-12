package ua.lardi.phoneBook.dao.jdbcDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Repository
@Profile("default")
public class ContactJDBCDaoImpl extends AbstractJDBCDao implements ContactDao {

    @Autowired
    private RowMapper<Contact> contactRowMapper;

    @Override
    public void save(Contact object) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO contact (lastname, firstname, middlename, mobilephone, " +
                "homephone, address, email, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().update(connection -> makeBasePrepareStatment(sql, object, connection), holder);

        //return findById(parseLongFromHolder(holder));
    }

    @Override
    public Contact findById(long id) {
        final String sql = "SELECT * FROM contact WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, contactRowMapper);
    }

    @Override
    public void update(Contact object) {
        final String query = "UPDATE contact SET lastname = ?, firstname = ?, middlename = ?, " +
                "mobilephone = ?, homephone = ?, address = ?, email = ?, user_id = ? WHERE id = ?";
        getJdbcTemplate().update(connection -> {
            PreparedStatement statement = makeBasePrepareStatment(query, object, connection);
            statement.setLong(9, object.getId());
            return statement;
        });
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM contact WHERE id = ";
        getJdbcTemplate().update(sql + id);
    }

    @Override
    public List<Contact> findAll() {
        final String sql = "SELECT * FROM contact";
        return getJdbcTemplate().query(sql, contactRowMapper);
    }

    @Override
    public List<Contact> findAllContactsByUser(User user) {
        final String sql = "SELECT * FROM contact WHERE user_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{user.getId()}, contactRowMapper);
    }

    private PreparedStatement makeBasePrepareStatment(String query, Contact contact, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, contact.getLastName());
        statement.setString(2, contact.getFirstName());
        statement.setString(3, contact.getMiddleName());
        statement.setString(4, contact.getMobilePhone());
        statement.setString(5, contact.getHomePhone());
        statement.setString(6, contact.getAddress());
        statement.setString(7, contact.getEmail());
        statement.setLong(8, contact.getUser().getId());
        return statement;
    }
}
