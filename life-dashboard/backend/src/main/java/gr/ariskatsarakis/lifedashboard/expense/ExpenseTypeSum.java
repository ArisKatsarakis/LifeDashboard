package gr.ariskatsarakis.lifedashboard.expense;

import java.math.BigDecimal;
import java.util.List;

/**
 * ExpenseTypeSum
 */
public class ExpenseTypeSum {
  private BigDecimal expenseSum;
  private List<Expense> expenses;

  public void setExpenseSum(BigDecimal expenseSum) {
    this.expenseSum = expenseSum;
  }

  public BigDecimal getExpenseSum() {
    return expenseSum;
  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }
}
