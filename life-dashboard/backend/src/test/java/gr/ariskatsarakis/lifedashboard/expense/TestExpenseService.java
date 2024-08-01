package gr.ariskatsarakis.lifedashboard.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import gr.ariskatsarakis.lifedashboard.income.IncomeRepository;
import gr.ariskatsarakis.lifedashboard.samples.Samples;

/**
 * TestExpenseService
 */
@AutoConfigureTestDatabase
public class TestExpenseService {

  @Autowired
  private ExpenseService expenseService = new ExpenseService();

  @Test
  public void test_ExpensesCrud() {
    this.expenseService.expenseRepository = Mockito.mock(ExpenseRepository.class);
    this.expenseService.incomeRepository = Mockito.mock(IncomeRepository.class);
    Expense testExpense = Samples.sampleExpense();
    this.expenseService.addExpense(testExpense);
    List<Expense> expenses = new ArrayList<>();
    expenses.add(testExpense);
    Mockito.when(expenseService.getExpense()).thenReturn(expenses);

    assertEquals(this.expenseService.getExpense().get(0).getMoney(), testExpense.getMoney());
    assertEquals(this.expenseService.getExpense().get(0).getExpenseId(), testExpense.getExpenseId());
  }

}
