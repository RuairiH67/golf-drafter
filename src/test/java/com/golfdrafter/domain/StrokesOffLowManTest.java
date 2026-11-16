package com.golfdrafter.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StrokesOffLowManTest {

    @Test
    void threePlayersAtFullAllowance_getTheFullGap() {
        var handicaps = List.of(
                new CourseHandicap(5),
                new CourseHandicap(10),
                new CourseHandicap(15));

        var strokes = new StrokesOffLowMan().strokesReceived(handicaps, 1.0);

        assertThat(strokes).containsExactly(0, 5, 10);
    }

    @Test
    void emptyList_isRejected() {
        assertThatThrownBy(() -> new StrokesOffLowMan().strokesReceived(List.of(), 1.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("At least one handicap must be given");
    }
}
