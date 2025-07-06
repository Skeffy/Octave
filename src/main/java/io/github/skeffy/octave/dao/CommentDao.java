package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Comment;
import io.github.skeffy.octave.model.User;

public interface CommentDao {

    Comment createComment(Comment comment);

    int deleteOwnComment(User user, int commentId);

    int adminDeleteComment(User user, int commentId);
}
