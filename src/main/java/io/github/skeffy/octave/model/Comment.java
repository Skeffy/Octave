package io.github.skeffy.octave.model;

import java.util.Date;

public class Comment {

    private User user;
    private String body;
    private Date date;
    private int likes;
    private int parentId;

    public Comment(User user, String body, Date date, int likes, int parentId) {
        this.user = user;
        this.body = body;
        this.date = date;
        this.likes = likes;
        this.parentId = parentId;
    }

    public Comment() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
