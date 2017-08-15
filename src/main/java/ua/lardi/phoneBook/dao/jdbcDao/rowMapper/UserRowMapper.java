package ua.lardi.phoneBook.dao.jdbcDao.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        
        return user;
    }
}
