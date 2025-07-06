package io.github.skeffy.octave.posting.dao;

import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.User;

public interface PostDao {

    Post createPost(Post post);

    int deleteOwnPost(User user, int postId);

    int adminDeletePost(User user, int postId);
}
