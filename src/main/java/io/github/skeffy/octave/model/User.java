package io.github.skeffy.octave.model;

public class User {

    private int id;
    private String username;
    private String screenName;
    private String photo;

    public User(int id, String username, String screenName, String photo) {
        this.id = id;
        this.username = username;
        this.screenName = screenName;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
