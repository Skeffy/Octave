package io.github.skeffy.octave.dao;

public interface LikeDao {

    int addLike(int userId, int postId);

    int removeLike(int userId, int postId);
}
