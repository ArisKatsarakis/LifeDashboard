package gr.ariskatsarakis.lifedashboard.expense.beans;

import gr.ariskatsarakis.lifedashboard.expense.def.Expense;

import java.math.BigDecimal;
import java.util.List;

public class ExpensesByTypeDTO {

  private ExpenseType expenseType;

  private List<Expense> expenses;

  private BigDecimal moneySum;

  public ExpensesByTypeDTO(List<Expense> sameTypeExpenses, ExpenseType expenseType) {
    this.expenses = sameTypeExpenses;
    this.expenseType = expenseType;

  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public void setExpenseType(ExpenseType expenseType) {
    this.expenseType = expenseType;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  public ExpenseType getExpenseType() {
    return expenseType;
  }

  public void setMoneySum(BigDecimal moneySum) {
    this.moneySum = moneySum;
  }

  public BigDecimal getMoneySum() {
    return moneySum;
  }
}
