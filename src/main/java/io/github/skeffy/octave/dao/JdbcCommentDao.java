package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Comment;
import io.github.skeffy.octave.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcCommentDao implements CommentDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Comment createComment(Comment comment) {
        return null;
    }

    @Override
    public int deleteOwnComment(User user, int commentId) {
        return 0;
    }

    @Override
    public int adminDeleteComment(User user, int commentId) {
        return 0;
    }
}
