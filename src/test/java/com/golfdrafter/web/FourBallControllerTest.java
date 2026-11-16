package com.golfdrafter.web;

import com.golfdrafter.config.DomainConfig;
import com.golfdrafter.domain.CourseHandicap;
import com.golfdrafter.domain.FourBallMatchPlay;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FourBallController.class)
class FourBallControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    FourBallMatchPlay fourBall;

    @Test
    void fourHandicaps_returnStrokes() throws Exception {
        when(fourBall.strokesReceived(any())).thenReturn(List.of(0, 7, 15, 26));
        mockMvc.perform(post("/api/four-ball/strokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"handicaps": [10, 18, 27, 39]}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strokes[0]").value(0))
                .andExpect(jsonPath("$.strokes[1]").value(7))
                .andExpect(jsonPath("$.strokes[2]").value(15))
                .andExpect(jsonPath("$.strokes[3]").value(26));

        verify(fourBall).strokesReceived(List.of(
                new CourseHandicap(10),
                new CourseHandicap(18),
                new CourseHandicap(27),
                new CourseHandicap(39)));
    }

    @Test
    void threeHandicaps_returnBadRequest() throws Exception {
        when(fourBall.strokesReceived(any())).thenThrow(new IllegalArgumentException("Four-ball needs exactly 4 players, but got 3"));
        mockMvc.perform(post("/api/four-ball/strokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"handicaps": [5, 10, 15]}
                            """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(containsString("exactly 4 players")));
        verify(fourBall).strokesReceived(List.of(
                new CourseHandicap(5),
                new CourseHandicap(10),
                new CourseHandicap(15))
        );
    }
}