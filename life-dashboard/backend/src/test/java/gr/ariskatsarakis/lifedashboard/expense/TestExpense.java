package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * TestExpense
 */
public class TestExpense {

  @Test
  public void test_createExpenses() {
    Expense expense = new Expense();
    expense.setMoney(BigDecimal.TEN);
    assert (expense.getMoney().equals(BigDecimal.TEN));
  }

  @Test
  public void test_AddExpenses() {
    Expense expense = new Expense();
    expense.setMoney(BigDecimal.TEN);

    Expense expense2 = new Expense();
    expense2.setMoney(BigDecimal.TEN);
    BigDecimal sum = expense2.getMoney().add(expense.getMoney());
    assert (sum.equals(BigDecimal.valueOf(20l)));
  }

}
