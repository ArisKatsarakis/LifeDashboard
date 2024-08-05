package gr.ariskatsarakis.lifedashboard.expense;

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
 * TestExpensesController
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestExpensesController {

  @Autowired
  private MockMvc mockMvc;

  private String testUrl = "/api/v1/expenses";

  private String authoriztionUrl = "/auth/login";

  private ObjectMapper objectMapper = new ObjectMapper();

  private JwtResponse jwtResponse;

  @BeforeAll
  void setupCalls() throws Exception {

    JwtRequest input = new JwtRequest();
    input.setUsername("katsar");
    input.setPassword("test");
    MvcResult mvcResult = mockMvc
        .perform(
            post(authoriztionUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(input.toString())
                .characterEncoding("utf-8"))
        .andReturn();
    this.jwtResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JwtResponse.class);
  }

  @Test
  void testGetExpenses() throws Exception {
    MvcResult mvcResExpense = mockMvc.perform(
        get(testUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + jwtResponse.getToken()))
        .andReturn();

    Expense[] expenses = objectMapper.readValue(mvcResExpense.getResponse().getContentAsString(), Expense[].class);
    for (Expense e : expenses) {
      if (e.getExpenseId().equals(20L)) {
        assertThat(e.getMoney().toString()).isEqualTo("20.00");
      }
    }
  }

  @Test
  void testAddingExpenses() throws Exception {
    Expense expense = new Expense();
    expense.setName("Testing Expense");
    expense.setMoney(BigDecimal.valueOf(40L));
    expense.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
    MvcResult mvcResult = mockMvc.perform(
        post(testUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .content(expense.toString())
            .header("Authorization", "Bearer " + jwtResponse.getToken())

    ).andReturn();

    System.out.println(mvcResult.getResponse().getContentAsString());
    Expense result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
        Expense.class);
    assertThat(result.getMoney()).isEqualTo(BigDecimal.valueOf(40L));
  }
}
