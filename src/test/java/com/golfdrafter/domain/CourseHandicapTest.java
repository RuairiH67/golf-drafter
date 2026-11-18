package com.golfdrafter.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CourseHandicapTest {

    @Test
    void storesItsValue() {
        var courseHandicap = new CourseHandicap(18);

        assertThat(courseHandicap.handicap()).isEqualTo(18);
    }

    @Test
    void plusHandicap_isAllowed() {
        assertThat(new CourseHandicap(-4).handicap()).isEqualTo(-4);
    }

    @Test
    void handicapAboveRange_isRejected() {
        assertThatThrownBy(() -> new CourseHandicap(999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("between -10 and 80");
    }

    @Test
    void handicapBelowRange_isRejected() {
        assertThatThrownBy(() -> new CourseHandicap(-11))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("between -10 and 80");
    }
}
