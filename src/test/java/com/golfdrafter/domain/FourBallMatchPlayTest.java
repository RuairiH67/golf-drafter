package com.golfdrafter.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FourBallMatchPlayTest {
    @Test
    void officialExample_GiveStrokesOffOfLowManTest() {
        var handicaps = List.of(
                new CourseHandicap(10),
                new CourseHandicap(18),
                new CourseHandicap(27),
                new CourseHandicap(39));

        var strokes = new FourBallMatchPlay().strokesReceived(handicaps);

        assertThat(strokes).containsExactly(0, 7, 15, 26);
    }

    @Test
    void halfStrokes_RoundUp() {
        var handicaps = List.of(
                new CourseHandicap(5),
                new CourseHandicap(10),
                new CourseHandicap(15),
                new CourseHandicap(20));

        var strokes = new FourBallMatchPlay().strokesReceived(handicaps);

        assertThat(strokes).containsExactly(0, 5, 9, 14);
    }

    @Test
    void threePlayers_isRejected() {
        var handicaps = List.of(
                new CourseHandicap(5),
                new CourseHandicap(10),
                new CourseHandicap(15));

        assertThatThrownBy(() -> new FourBallMatchPlay().strokesReceived(handicaps))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("exactly 4 players");
    }
}
