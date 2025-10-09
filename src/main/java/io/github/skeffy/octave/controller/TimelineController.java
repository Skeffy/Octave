package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.exception.DaoException;
import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.service.PrincipalService;
import io.github.skeffy.octave.service.TimelineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineService timelineService;

    public TimelineController(TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @GetMapping("/default")
    public Timeline getUnauthenticatedTimeline() {
        try {
            return timelineService.createUnauthenticatedTimeline();
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }

    @GetMapping("/custom")
    public Timeline getCustomUserTimeline(Principal principal) {
        try {
            int userId = PrincipalService.getUserId(principal);
            return timelineService.createUserSpecificTimeline(userId);
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }
    }
}
