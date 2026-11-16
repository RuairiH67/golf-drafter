package com.golfdrafter.web;

import com.golfdrafter.domain.CourseHandicap;
import com.golfdrafter.domain.FourBallMatchPlay;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/four-ball")
public class FourBallController {

    private final FourBallMatchPlay fourBall;

    public FourBallController(FourBallMatchPlay fourBall) {
        this.fourBall = fourBall;
    }

    @PostMapping("/strokes")
    public StrokesResponse strokes (@RequestBody StrokesRequest request) {
        var handicaps = request.handicaps().stream()
                .map(CourseHandicap::new)
                .toList();

        return new StrokesResponse(fourBall.strokesReceived(handicaps));
    }

    public record StrokesRequest(List<Integer> handicaps) {}

    public record StrokesResponse(List<Integer> strokes) {}
}
