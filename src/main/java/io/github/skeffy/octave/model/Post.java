package io.github.skeffy.octave.model;

import java.util.Date;
import java.util.List;

public class Post {

    private int postId;
    private String type;
    private User user;
    private String body;
    private Date date;
    private int likes;
    private List<Comment> comments;

    public Post(int postId, String type, User user, String body, Date date, int likes, List<Comment> comments) {
        this.postId = postId;
        this.type = type;
        this.user = user;
        this.body = body;
        this.date = date;
        this.likes = likes;
        this.comments = comments;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
