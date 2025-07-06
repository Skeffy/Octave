package io.github.skeffy.octave.controller;

import io.github.skeffy.octave.model.Timeline;
import io.github.skeffy.octave.dao.TimelineDao;
import io.github.skeffy.octave.service.TimelineCompiler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/timeline")
public class TimelineController {

    private final TimelineDao timelineDao;
    private final TimelineCompiler timelineCompiler;

    public TimelineController(TimelineDao timelineDao, TimelineCompiler timelineCompiler) {
        this.timelineDao = timelineDao;
        this.timelineCompiler = timelineCompiler;
    }

    @GetMapping("/default")
    public Timeline getUnauthenticatedTimeline() {
        return timelineCompiler.createUnauthenticatedTimeline();
    }

    @GetMapping("/custom")
    public Timeline getCustomUserTimeline(Principal principal) {
        //TODO: get userId from principal
        return timelineCompiler.createUserSpecificTimeline(1);
    }
}
