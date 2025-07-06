package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.dao.TimelineDao;
import io.github.skeffy.octave.service.TimelineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineDao timelineDao;
    private final TimelineService timelineService;

    public TimelineController(TimelineDao timelineDao, TimelineService timelineService) {
        this.timelineDao = timelineDao;
        this.timelineService = timelineService;
    }

    @GetMapping("/default")
    public Timeline getUnauthenticatedTimeline() {
        return timelineService.createUnauthenticatedTimeline();
    }

    @GetMapping("/custom")
    public Timeline getCustomUserTimeline(Principal principal) {
        //TODO: get userId from principal
        return timelineService.createUserSpecificTimeline(1);
    }
}
