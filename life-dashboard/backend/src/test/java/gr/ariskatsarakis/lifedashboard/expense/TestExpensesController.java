package gr.ariskatsarakis.lifedashboard.expense;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import gr.ariskatsarakis.lifedashboard.jwt.*;

/**
 * TestExpensesController
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestExpensesController {

  // @LocalServerPort
  // private int port;
  // @Autowired
  // TestRestTemplate requestTemplate;

  @Autowired
  private MockMvc mockMvc;

  private String testUrl = "/api/v1/expenses";

  @Test
  void testGetExpenses() throws Exception {
    JwtRequest input = new JwtRequest();
    input.setUsername("katsar");
    input.setUsername("user");

    this.mockMvc.perform(get(this.testUrl)).andDo(print()).andExpect(status().is(401));
    mockMvc
        .perform(
            post("/auth/login")
                .content(input.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andDo(print());
  }
}
