package io.github.skeffy.octave.timeline.dao;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTimelineDao implements TimelineDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTimelineDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Post> getFollowingPosts(int userId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * from posts WHERE";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if(results.next()) {
                posts.add(mapRowToPost(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to database", e);
        }
        return posts;
    }

    @Override
    public List<Post> getPostsFromUserLikes(int userId) {
        return List.of();
    }

    @Override
    public List<Post> getTrendingPosts() {
        return List.of();
    }

    @Override
    public List<Post> getPostsFromPopularAccounts() {
        return List.of();
    }

    @Override
    public List<Post> getRecentPosts() {
        return List.of();
    }

    private Post mapRowToPost(SqlRowSet r) {
        Post p = new Post();
        //TODO: This is going to need so much rewriting when I actually layout the db schema
        p.setUser(r.getObject("userId", User.class));
        p.setType(r.getString("type"));
        p.setBody(r.getString("body"));
        p.setDate(r.getDate("timestamp"));
        p.setLikes(r.getInt("likeCount"));
        p.setComments(r.getObject("comments", List.class));
        return p;
    }
}
