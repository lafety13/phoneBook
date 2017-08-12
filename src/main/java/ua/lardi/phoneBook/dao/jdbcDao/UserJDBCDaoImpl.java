package ua.lardi.phoneBook.dao.jdbcDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;


@Repository
@Profile("default")
public class UserJDBCDaoImpl extends AbstractJDBCDao implements UserDao {
    private static final String GENERATED_KEY_NAME = System.getProperty("GENERATED_KEY_NAME");

    @Autowired
    private RowMapper<User> userRowMapper;

    @Override
    public void save(User object) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO users (login, password, name) VALUES (?, ?, ?)";
        getJdbcTemplate().update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            return statement;
        }, holder);
    }

    @Override
    public User findById(long id) {
        final String sql = "SELECT * FROM users WHERE id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, userRowMapper);
    }

    @Override
    public void update(User object) {
        final String sql = "UPDATE users SET login = ?, password = ?, name = ? WHERE id = ?";
        getJdbcTemplate().update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getName());
            statement.setLong(4, (object.getId() == null) ? 0 : object.getId());
            return statement;
        });
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM users WHERE id = ";
        getJdbcTemplate().update(sql + id);
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT * FROM users";
        return getJdbcTemplate().query(sql, userRowMapper);
    }

    @Override
    public User findByLogin(String login) {
        final String sql = "SELECT * FROM users WHERE login = ?";
        try {
            return getJdbcTemplate().queryForObject(sql, new Object[]{login}, userRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
