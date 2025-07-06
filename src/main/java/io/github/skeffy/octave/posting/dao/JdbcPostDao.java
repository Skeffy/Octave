package io.github.skeffy.octave.posting.dao;

import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcPostDao implements PostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public int deleteOwnPost(User user, int postId) {
        return 0;
    }

    @Override
    public int adminDeletePost(User user, int postId) {
        return 0;
    }
}
