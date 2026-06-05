import org.example.petroutine.controller.ActivityController;
import org.example.petroutine.service.ActivityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ActivityController.class)
class ActivityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @Test
    void testValidActivityLog_Returns201() throws Exception {
        String jsonBody = """
                {
                  "activityType": "FEEDING",
                  "performedAt": "2026-05-31T20:15:00Z",
                  "notes": "Дав 40г сухого корму"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pets/12/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testInvalidActivityType_Returns400() throws Exception {
        String jsonBody = """
                {
                  "activityType": "FLYING",
                  "performedAt": "2026-05-31T20:15:00Z"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/pets/12/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testGetTodayActivitiesForNewPet_Returns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pets/99/activities/today"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}