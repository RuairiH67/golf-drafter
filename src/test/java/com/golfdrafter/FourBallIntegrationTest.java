package com.golfdrafter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FourBallIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void officialExample_worksEndToEnd() throws Exception {
        mockMvc.perform(post("/api/four-ball/strokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"handicaps": [10, 18, 27, 39]}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strokes[1]").value(7))
                .andExpect(jsonPath("$.strokes[3]").value(26));
    }

    @Test
    void threePlayers_areRejectedEndToEnd() throws Exception {
        mockMvc.perform(post("/api/four-ball/strokes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"handicaps": [5, 10, 15]}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value(containsString("exactly 4 players")));
    }
}