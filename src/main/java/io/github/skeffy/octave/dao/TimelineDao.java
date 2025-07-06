package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Post;

import java.util.List;

public interface TimelineDao {

    List<Post> getFollowingPosts(int userId);

    List<Post> getPostsFromUserLikes(int userId);

    List<Post> getTrendingPosts();

    List<Post> getPostsFromPopularAccounts();

    List<Post> getRecentPosts();
}
