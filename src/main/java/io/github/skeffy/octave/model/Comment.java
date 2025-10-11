package io.github.skeffy.octave.model;

import java.util.Date;

public class Comment {

    private int commentId;
    private int userId;
    private String body;
    private Date date;
    private int likes;
    private int parentId;
    private boolean isVisible;

    public Comment(int commentId, int userId, String body, Date date, int likes, int parentId, boolean isVisible) {
        this.commentId = commentId;
        this.userId = userId;
        this.body = body;
        this.date = date;
        this.likes = likes;
        this.parentId = parentId;
        this.isVisible = isVisible;
    }

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
