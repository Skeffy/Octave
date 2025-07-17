package io.github.skeffy.octave.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcFollowDao implements FollowDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFollowDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int getFollowers(int userId) {
        return 0;
    }

    @Override
    public int getFollowing(int userId) {
        return 0;
    }

    @Override
    public int addFollower(int followerId, int followingId) {
        return 0;
    }

    @Override
    public int removeFollower(int followerId, int followingId) {
        return 0;
    }
}
