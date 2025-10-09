package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Comment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcCommentDao implements CommentDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Comment getCommentById(int commentId) {
        Comment comment = null;
        String sql = "SELECT * FROM comments WHERE comment_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, commentId);
            if(results.next()) {
                comment = mapRowToComment(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }

        return comment;
    }

    @Override
    public Comment createComment(Comment c) {
        Comment newComment;
        String sql = "INSERT INTO comments(user_id, post_id, body) VALUES(?, ?, ?) RETURNING comment_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, c.getUserId(), c.getParentId(), c.getBody());
            newComment = getCommentById(newId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newComment;
    }

    @Override
    public int deleteOwnComment(int userId, int commentId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM comments WHERE comment_id = ? AND user_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, commentId, userId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    @Override
    public int adminDeleteComment(int userId, int commentId) {
        return 0;
    }

    private Comment mapRowToComment(SqlRowSet r) {
        Comment c = new Comment();
        c.setCommentId(r.getInt("comment_id"));
        c.setUserId(r.getInt("user_id"));
        c.setDate(r.getDate("date"));
        c.setParentId(r.getInt("parent_id"));
        c.setBody(r.getString("body"));
        c.setLikes(r.getInt("likes"));
        return c;
    }
}
