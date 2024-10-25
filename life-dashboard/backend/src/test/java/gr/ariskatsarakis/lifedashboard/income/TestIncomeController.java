package gr.ariskatsarakis.lifedashboard.income;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
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
// import static org.assertj.core.api.Assertions.assertThat;
import gr.ariskatsarakis.lifedashboard.TestingURLs;
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
        post(TestingURLs.AUTH_URL)
            .content(input.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
        .andReturn();
    this.jwtResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(),
        JwtResponse.class);
  }

  @Test
  void createIncome_Test() throws Exception {
    MvcResult incomeResult = mockMvc.perform(
        post(TestingURLs.INCOMES_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(sampleIncome().toString())
            .header("Authorization", String.format("Bearer %s", jwtResponse.getToken())))
        .andReturn();
    System.out.println(incomeResult.getResponse().getContentAsString());
    Income income = objectMapper.readValue(incomeResult.getResponse().getContentAsString(), Income.class);
    Assertions.assertThat(income.getMoney().equals(sampleIncome().getMoney()));
  }

  Income sampleIncome() {
    Income income = new Income();
    income.setMoney(BigDecimal.valueOf(10000L));
    income.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    income.setIncomeType(IncomeType.NOVA);
    return income;
  }
}
