package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcUserDao implements UserDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getUserIdByUsername(String username) {
        return 0;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }
}
