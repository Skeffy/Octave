package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Post;

public interface PostDao {

    Post createPost(Post post);

    int deleteOwnPost(int userId, int postId);

    int adminDeletePost(int userId, int postId);
}
