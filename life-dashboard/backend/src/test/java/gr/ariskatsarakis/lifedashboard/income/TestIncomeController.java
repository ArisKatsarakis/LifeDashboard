package gr.ariskatsarakis.lifedashboard.income;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
 * TestIncomeController
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestIncomeController {
  @Autowired
  MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();
  private JwtResponse jwtResponse;

  @BeforeAll
  void setup() throws Exception {

    JwtRequest input = new JwtRequest();
    input.setUsername("katsar");
    input.setPassword("test");

    MvcResult authResult = mockMvc.perform(
        post("/auth/login")
            .content(input.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
        .andReturn();
    this.jwtResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(), JwtResponse.class);
  }

  @Test
  void create2DifferentIcome_test() throws Exception {
    Income novaIncome = new Income();
    novaIncome.setMoney(BigDecimal.valueOf(10000L));
    novaIncome.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    novaIncome.setIncomeType(IncomeType.NOVA);

    MvcResult insertIncomeResult = mockMvc.perform(
        post("/api/v1/incomes")
            .header("Authorization", "Bearer " + jwtResponse.getToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(novaIncome.toString()))
        .andReturn();

    Income output = objectMapper.readValue(insertIncomeResult.getResponse().getContentAsString(), Income.class);
    assertThat(output.getIncomeType()).isEqualTo(novaIncome.getIncomeType());

    Income athaliIncome = new Income();
    athaliIncome.setMoney(BigDecimal.valueOf(10000L));
    athaliIncome.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    athaliIncome.setIncomeType(IncomeType.ATHANLI);

    MvcResult insertAthaliIncomeResult = mockMvc.perform(
        post("/api/v1/incomes")
            .header("Authorization", "Bearer " + jwtResponse.getToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(athaliIncome.toString()))
        .andReturn();

    output = objectMapper.readValue(insertAthaliIncomeResult.getResponse().getContentAsString(), Income.class);
    assertThat(output.getIncomeType()).isEqualTo(athaliIncome.getIncomeType());
  }

  @Test
  void getTestIncomes_test() throws Exception {
    MvcResult getIncomeWithId1001Result = mockMvc.perform(
        get("/api/v1/incomes")
            .header("Authorization", "Bearer " + jwtResponse.getToken()))
        .andReturn();

    System.out.println(getIncomeWithId1001Result.getResponse().getContentAsString());
  }
}
