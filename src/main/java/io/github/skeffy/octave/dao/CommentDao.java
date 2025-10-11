package io.github.skeffy.octave.dao;

import io.github.skeffy.octave.model.Comment;

import java.util.List;

public interface CommentDao {

    Comment getCommentById(int commentId);

    List<Comment> getCommentsByParentId(int parentId);

    Comment createComment(int userId, Comment comment);

    int deleteOwnComment(int userId, int commentId);

    int adminDeleteComment(int commentId);
}
