package com.golfdrafter.domain;

public record CourseHandicap(int handicap) {

    private static final int HANDICAP_MIN = -10;
    private static final int HANDICAP_MAX = 80;

    public CourseHandicap {
        if (handicap > HANDICAP_MAX || handicap < -HANDICAP_MIN) {
            throw new IllegalArgumentException("Handicap must be between -10 and 80 -> '" + handicap + "'");
        }
    }
}
