package io.github.skeffy.octave.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcLikeDao implements LikeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLikeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int addLike(int userId, int postId) {
        return 0;
    }

    @Override
    public int removeLike(int userId, int postId) {
        return 0;
    }
}
