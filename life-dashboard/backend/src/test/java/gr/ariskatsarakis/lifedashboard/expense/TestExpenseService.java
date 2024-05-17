package gr.ariskatsarakis.lifedashboard.expense;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TestExpenseService
 */
public class TestExpenseService {

  @Autowired
  private ExpenseService expenseService = new ExpenseService();

  @Test
  /***
   * TODO create the functions addExpense and test them .
   * Also the same for update delete.
   * Finally get all expenses nad individudally expenses
   */
  public void tet_addExpenses() {
    assert (expenseService.getExpense().isEmpty() == true);
  }

}
