package io.github.skeffy.octave.model;

import java.util.ArrayList;
import java.util.List;

public class Timeline {

    List<Post> posts = new ArrayList<>();

    public Timeline(List<Post> posts) {
        this.posts = posts;
    }

    public Timeline() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
