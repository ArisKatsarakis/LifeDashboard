package gr.ariskatsarakis.lifedashboard.expense;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

  // @Autowired
  // private MockMvc mockMvc;
  //
  // private String expenseUrl = "/api/v1/expenses";
  // private String expenseTypeUrl = "/api/v1/expense-types";
  //
  // private String authoriztionUrl = "/auth/login";
  //
  // private ObjectMapper objectMapper = new ObjectMapper();
  //
  // private JwtResponse jwtResponse;
  //
  // private Expense sample;
  //
  // @BeforeAll
  // void setupCalls() throws Exception {
  //
  // sample = new Expense();
  // sample.setExpenseId(101L);
  // sample.setMoney(BigDecimal.valueOf(40L));
  // sample.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
  // sample.setName("Sample Expense");
  //
  // JwtRequest input = new JwtRequest();
  // input.setUsername("katsar");
  // input.setPassword("test");
  // MvcResult mvcResult = mockMvc
  // .perform(
  // post(authoriztionUrl)
  // .contentType(MediaType.APPLICATION_JSON)
  // .content(input.toString())
  // .characterEncoding("utf-8"))
  // .andReturn();
  // this.jwtResponse =
  // objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
  // JwtResponse.class);
  // }
  //
  // /**
  // *
  // * 1. Create Token - Done
  // * 2. Create Expense Type - Done
  // * 3. Get Expense Type - Done
  // * 4. Create expense for this expense type. - Done
  // * 5. Get Expense Type and Expense - Done
  // * 6. Update Expense - Done
  // * 7. delete expense
  // * 8. delete expense type
  // */
  // @Test
  // void addExpenseAndExpenseType() throws Exception {
  // String name = "New Expense Type";
  // ExpenseType expType = new ExpenseType();
  // expType.setExpenseTypeName(name);
  // MvcResult result = mockMvc.perform(
  // post(expenseTypeUrl)
  // .header("Authorization", "Bearer " + jwtResponse.getToken())
  // .contentType(MediaType.APPLICATION_JSON)
  // .content(expType.toString()))
  // .andReturn();
  // expType = objectMapper.readValue(result.getResponse().getContentAsString(),
  // ExpenseType.class);
  // assertThat(expType.getExpenseTypeName()).isEqualTo(name);
  // String expensePointUrl = "/api/v1/expense-types/" +
  // expType.getExpenseTypeId().toString() + "/expenses";
  // MvcResult expenseResult = mockMvc.perform(
  // post(expensePointUrl)
  // .header("Authorization", "Bearer " + jwtResponse.getToken())
  // .contentType(MediaType.APPLICATION_JSON)
  // .content(sample.toString()))
  // .andReturn();
  // System.out.println(expenseResult.getResponse().getContentAsString());
  // sample =
  // objectMapper.readValue(expenseResult.getResponse().getContentAsString(),
  // Expense.class);
  // MvcResult resultForGetting = mockMvc.perform(
  // get("/api/v1/expense-types/" + expType.getExpenseTypeId().toString())
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  // expType =
  // objectMapper.readValue(resultForGetting.getResponse().getContentAsString(),
  // ExpenseType.class);
  //
  // assertThat(expType.getExpense().get(0).getMoney().equals(sample.getMoney()));
  // assertThat(expType.getExpense().get(0).getName().equals(sample.getName()));
  //
  // sample.setMoney(sample.getMoney().add(BigDecimal.valueOf(20L)));
  // MvcResult updateExpenseResult = mockMvc.perform(
  // put("/api/v1/expense-types/" + expType.getExpenseTypeId() + "/expenses")
  // .header("Authorization", "Bearer " + jwtResponse.getToken())
  // .contentType(MediaType.APPLICATION_JSON)
  // .content(sample.toString()))
  // .andReturn();
  //
  // Expense updatedSample =
  // objectMapper.readValue(updateExpenseResult.getResponse().getContentAsString(),
  // Expense.class);
  //
  // assertThat(updatedSample.getMoney().equals(sample.getMoney()));
  //
  // MvcResult mvcResultj = mockMvc
  // .perform(delete("/api/v1/expense-types/" + expType.getExpenseTypeId() +
  // "/expenses/" + sample.getExpenseId())
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  // assertThat(mvcResultj.getResponse().getStatus()).isEqualTo(200);
  //
  // // check if sample still exists
  // //
  // MvcResult stillExistsResult = mockMvc.perform(
  // get("/api/v1/expenses/" + sample.getExpenseId())
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  //
  // assertThat(stillExistsResult.getResponse().getStatus()).isEqualTo(404);
  //
  // // check if exists in the expenseType
  // stillExistsResult = mockMvc.perform(
  // get("/api/v1/expense-types/" + expType.getExpenseTypeId())
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  //
  // expType =
  // objectMapper.readValue(stillExistsResult.getResponse().getContentAsString(),
  // ExpenseType.class);
  // assertThat(expType.getExpense().indexOf(sample)).isEqualTo(-1);
  //
  // MvcResult deleteExpenseTypeResult = mockMvc.perform(
  // delete("/api/v1/expense-types/" + expType.getExpenseTypeId())
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  //
  // assertThat(deleteExpenseTypeResult.getResponse().getStatus()).isEqualTo(200);
  //
  // MvcResult getExpensesTypesResult = mockMvc.perform(
  // get("/api/v1/expense-types")
  // .header("Authorization", "Bearer " + jwtResponse.getToken()))
  // .andReturn();
  //
  // ExpenseType[] expenseTypesAfterDelete = objectMapper
  // .readValue(getExpensesTypesResult.getResponse().getContentAsString(),
  // ExpenseType[].class);
  //
  // for (ExpenseType e : expenseTypesAfterDelete) {
  // assertThat(e).isNotEqualTo(expType);
  // }
  //
  // }

}
