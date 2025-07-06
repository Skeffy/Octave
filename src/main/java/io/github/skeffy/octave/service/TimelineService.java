package io.github.skeffy.octave.service;

import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.dao.TimelineDao;

import java.util.ArrayList;
import java.util.List;

public class TimelineService {

    private final TimelineDao timelineDao;

    public TimelineService(TimelineDao timelineDao) {
        this.timelineDao = timelineDao;
    }

    public Timeline createUnauthenticatedTimeline() {
        Timeline timeline = new Timeline();
        List<Post> posts = new ArrayList<>();
        posts.addAll(timelineDao.getTrendingPosts());
        posts.addAll(timelineDao.getPostsFromPopularAccounts());
        posts.addAll(timelineDao.getRecentPosts());
        //TODO: sort list by post date
        timeline.setPosts(posts);
        return timeline;
    }

    public Timeline createUserSpecificTimeline(int userId) {
        Timeline timeline = new Timeline();
        List<Post> posts = new ArrayList<>();
        posts.addAll(timelineDao.getFollowingPosts(userId));
        posts.addAll(timelineDao.getPostsFromUserLikes(userId));
        posts.addAll(timelineDao.getTrendingPosts());
        //TODO: sort list by post date
        timeline.setPosts(posts);
        return timeline;
    }
}
