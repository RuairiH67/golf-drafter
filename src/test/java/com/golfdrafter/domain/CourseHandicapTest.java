package com.golfdrafter.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseHandicapTest {

    @Test
    void storesItsValue() {
        var courseHandicap = new CourseHandicap(19);

        assertThat(courseHandicap.handicap()).isEqualTo(18);
    }
}
