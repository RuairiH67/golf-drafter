package com.golfdrafter.domain;

import java.util.ArrayList;
import java.util.List;


public class FourBallMatchPlay {

    private static final double HANDICAP_ALLOWANCE = 0.9;

    public List<Integer> strokesReceived(List<CourseHandicap> handicaps) {

        if(handicaps.isEmpty()) {
            throw new IllegalArgumentException("At least one handicap must be given");
        }

        int lowestHandicap = handicaps.stream()
                .mapToInt(CourseHandicap::handicap)
                .min()
                .orElseThrow();

        var strokes = new ArrayList<Integer>();

        for(CourseHandicap player : handicaps) {
           int gap = player.handicap() - lowestHandicap;
           strokes.add(Math.toIntExact(Math.round(gap * HANDICAP_ALLOWANCE)));
        }
        return strokes;
    }
}
