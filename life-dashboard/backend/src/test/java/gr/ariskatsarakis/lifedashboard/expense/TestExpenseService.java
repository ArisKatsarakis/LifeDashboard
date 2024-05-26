package gr.ariskatsarakis.lifedashboard.expense;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import gr.ariskatsarakis.lifedashboard.samples.Samples;

/**
 * TestExpenseService
 */
public class TestExpenseService {

  @Autowired
  private ExpenseService expenseService = new ExpenseService();

  @Test
  public void test_ExpensesCrud() {
    assert (expenseService.getExpense().isEmpty() == true);
    Expense sample = Samples.sampleExpense();
    expenseService.addExpense(sample);
    assert (expenseService.getExpense().isEmpty() == false);
    assert (expenseService.getExpenseById(sample.getExpenseId()).getMoney() == sample.getMoney());
    Expense sample2 = Samples.sampleExpense();
    sample2.setExpenseId(sample.getExpenseId());
    expenseService.updateExpense(sample2);
    assert (expenseService.getExpenseById(sample.getExpenseId()).getMoney() == sample2.getMoney());
  }

}
