package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Comment;
import io.github.skeffy.octave.model.User;

public interface CommentDao {

    Comment getCommentById(int commentId);

    Comment createComment(Comment comment);

    int deleteOwnComment(int userId, int commentId);

    int adminDeleteComment(int userId, int commentId);
}
