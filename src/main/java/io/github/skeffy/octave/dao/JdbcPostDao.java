package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Post;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post getPostById(int postId) {
        Post post = null;
        String sql = "SELECT * FROM posts WHERE post_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);
            if (results.next()) {
                post = mapRowToPost(results);
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByAccount(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ? ORDER BY date;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                posts.add(mapRowToPost(results));
            }
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        }
        return posts;
    }

    @Override
    public Post createPost(int userId, Post p) {
        Post newPost;
        String sql = "INSERT INTO posts(user_id, type, body) VALUES(?,?,?) RETURNING post_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, userId, p.getType(), p.getBody());
            newPost = getPostById(newId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newPost;
    }

    @Override
    public int deleteOwnPost(int userId, int postId) {
        int numberOfRows = 0;
        String sql = "UPDATE posts SET visible = 'false' WHERE user_id = ? AND post_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, userId, postId);
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
    public int adminDeletePost(int postId) {
        int numberOfRows = 0;
        String sql = "UPDATE posts SET visible = 'false' WHERE post_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, postId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }

    private Post mapRowToPost(SqlRowSet r) {
        Post p = new Post();
        p.setPostId(r.getInt("post_id"));
        p.setUserId(r.getInt("user_id"));
        p.setType(r.getString("type"));
        p.setBody(r.getString("body"));
        p.setDate(r.getDate("date"));
        p.setLikes(r.getInt("likes"));
        p.setComments(r.getInt("comments"));
        p.setIsVisible(r.getBoolean("visible"));
        return p;
    }
}
