package gr.ariskatsarakis.lifedashboard.expense;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
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
public class TestExpensesController {

  @Autowired
  private MockMvc mockMvc;

  private String testUrl = "/api/v1/expenses";

  private String authoriztionUrl = "/auth/login";

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void testGetExpenses() throws Exception {
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
    JwtResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), JwtResponse.class);

    MvcResult mvcResExpense = mockMvc.perform(
        get(testUrl)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer " + response.getToken()))
        .andReturn();

    Expense[] expenses = objectMapper.readValue(mvcResExpense.getResponse().getContentAsString(), Expense[].class);
    for (Expense e : expenses) {
      assertThat(e.getMoney().toString()).isEqualTo("20.00");
    }
  }
}
