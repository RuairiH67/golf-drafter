package com.golfdrafter.domain;

import java.util.List;


public class FourBallMatchPlay {

    private static final double HANDICAP_ALLOWANCE = 0.9;

    public List<Integer> strokesReceived(List<CourseHandicap> handicaps) {

        if (handicaps.size() != 4) {
            throw new IllegalArgumentException("Four-ball needs exactly 4 players, but got " + handicaps.size());
        }

        return new StrokesOffLowMan().strokesReceived(handicaps, HANDICAP_ALLOWANCE);
    }
}
