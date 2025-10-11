package io.github.skeffy.octave.model;

import java.util.Date;

public class Post {

    private int postId;
    private String type;
    private int userId;
    private String body;
    private Date date;
    private int likes;
    private int comments;
    private boolean isVisible;

    public Post(int postId, String type, int userId, String body, Date date, int likes, int comments, boolean isVisible) {
        this.postId = postId;
        this.type = type;
        this.userId = userId;
        this.body = body;
        this.date = date;
        this.likes = likes;
        this.comments = comments;
        this.isVisible = isVisible;
    }

    public Post() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
