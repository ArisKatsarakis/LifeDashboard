package gr.ariskatsarakis.lifedashboard.goal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.ariskatsarakis.lifedashboard.jwt.JwtRequest;
import gr.ariskatsarakis.lifedashboard.jwt.JwtResponse;

/**
 * TestSavingGoalController
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSavingGoalController {

  @Autowired
  MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();
  private JwtResponse jwtResponse;

  @BeforeAll
  void setup() throws Exception {

    JwtRequest input = new JwtRequest();
    input.setUsername("katsar");
    input.setPassword("test");

    MvcResult authResult = mockMvc
        .perform(
            post("/auth/login")
                .content(input.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
        .andReturn();

    this.jwtResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(),
        JwtResponse.class);

    System.out.println(authResult.getResponse().getContentAsString());
  }

  @Test
  void createNewSavingGoalWithoutWallet_Test() throws Exception {
    SavingGoal savingGoal = new SavingGoal();
    savingGoal.setTarget(BigDecimal.valueOf(100L));
    savingGoal.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
    LocalDateTime endDate = LocalDateTime.now().plusDays(30);
    savingGoal.setEndDate(Timestamp.valueOf(endDate));

    MvcResult creatingSavingGoalResult = mockMvc
        .perform(post("/api/v1/saving-goals"))
        .andReturn();
  }

}
