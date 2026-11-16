package com.golfdrafter.domain;

import java.util.ArrayList;
import java.util.List;

public class StrokesOffLowMan {

    public List<Integer> strokesReceived(List<CourseHandicap> handicaps, double allowance) {

        if (handicaps.isEmpty()) {
            throw new IllegalArgumentException("At least one handicap must be given");
        }

        int lowestHandicap = handicaps.stream()
                .mapToInt(CourseHandicap::handicap)
                .min()
                .orElseThrow();

        var strokes = new ArrayList<Integer>();

        for (CourseHandicap player : handicaps) {
            int gap = player.handicap() - lowestHandicap;
            strokes.add(Math.toIntExact(Math.round(gap * allowance)));
        }
        return strokes;
    }
}