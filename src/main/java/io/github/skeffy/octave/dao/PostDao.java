package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Post;

import java.util.List;

public interface PostDao {

    Post getPostById(int postId);

    List<Post> getPostsByAccount(int userId);

    Post createPost(int userId, Post post);

    int deleteOwnPost(int userId, int postId);

    int adminDeletePost(int postId);
}
