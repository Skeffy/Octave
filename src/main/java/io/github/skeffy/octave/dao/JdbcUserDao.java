package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcUserDao implements UserDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        Integer userId = null;
        String sql = "SELECT user_id FROM users WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if(results.next()) {
                userId = results.getInt("user_id");
            }
            return userId;
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if(results.next()) {
                user = mapRowToUser(results);
            }
            return user;
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
    }

    @Override
    public boolean getUserAdminStatusById(int userId) {
        boolean isAdmin = false;
        String sql = "SELECT admin FROM users WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if(results.next()) {
                isAdmin = results.getBoolean("admin");
            }
            return isAdmin;
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
    }

    private User mapRowToUser(SqlRowSet r) {
        User u = new User();
        u.setId(r.getInt("user_id"));
        u.setUsername(r.getString("username"));
        u.setScreenName(r.getString("screen_name"));
        u.setBio(r.getString("bio"));
        u.setIsAdmin(r.getBoolean("admin"));
        u.setPhoto(r.getString("photo"));
        return u;
    }
}
