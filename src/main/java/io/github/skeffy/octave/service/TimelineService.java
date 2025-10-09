package io.github.skeffy.octave.service;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Post;
import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.dao.TimelineDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            posts.addAll(timelineDao.getTrendingPosts());
            posts.addAll(timelineDao.getPostsFromPopularAccounts());
            posts.addAll(timelineDao.getRecentPosts());
            List<Post> sortedPosts = sortPosts(posts);
            timeline.setPosts(sortedPosts);
            return timeline;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    public Timeline createUserSpecificTimeline(int userId) {
        Timeline timeline = new Timeline();
        List<Post> posts = new ArrayList<>();
        try {
            posts.addAll(timelineDao.getFollowingPosts(userId));
            posts.addAll(timelineDao.getPostsFromUserLikes(userId));
            posts.addAll(timelineDao.getTrendingPosts());
            List<Post> sortedPosts = sortPosts(posts);
            timeline.setPosts(sortedPosts);
            return timeline;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    private List<Post> sortPosts(List<Post> posts) {
        posts.sort(Comparator.comparing(Post::getDate));
        return posts;
    }
}
