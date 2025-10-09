package io.github.skeffy.octave.service;

import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.dao.TimelineDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
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
        List<Post> sortedPosts = sortPosts(posts);
        timeline.setPosts(sortedPosts);
        return timeline;
    }

    public Timeline createUserSpecificTimeline(int userId) {
        Timeline timeline = new Timeline();
        List<Post> posts = new ArrayList<>();
        posts.addAll(timelineDao.getFollowingPosts(userId));
        posts.addAll(timelineDao.getPostsFromUserLikes(userId));
        posts.addAll(timelineDao.getTrendingPosts());
        List<Post> sortedPosts = sortPosts(posts);
        timeline.setPosts(sortedPosts);
        return timeline;
    }

    private List<Post> sortPosts(List<Post> posts) {
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }
}
