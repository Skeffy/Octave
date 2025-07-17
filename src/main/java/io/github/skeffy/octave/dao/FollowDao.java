package io.github.skeffy.octave.dao;

public interface FollowDao {

    int getFollowers(int userId);

    int getFollowing(int userId);

    int addFollower(int followerId, int followingId);

    int removeFollower(int followerId, int followingId);
}
